package com.recursos.service;

import com.recursos.exceptions.*;
import com.recursos.model.TareaDelParteDeHora;
import com.recursos.model.TipoEstado;
import com.recursos.repository.TareaDelParteDeHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TareaDelParteDeHorasService {

    @Autowired
    private TareaDelParteDeHorasRepository tareaDelParteDeHorasRepository;

    public TareaDelParteDeHora guardar(TareaDelParteDeHora tareaDelParteDeHora) {
        return tareaDelParteDeHorasRepository.save(tareaDelParteDeHora);
    }

    public Collection<TareaDelParteDeHora> getTareaDelParteDeHoras() { return tareaDelParteDeHorasRepository.findAll(); }


    public void deleteById(Long tareaDelParteDeHoraId) {
        tareaDelParteDeHorasRepository.deleteById(tareaDelParteDeHoraId);
    }

    public Date calcularSemanaAnterior() {
        Date todayDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(todayDate);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        return (c.getTime());
    }

    public void validarCantidadDeHorasTrabajadas (int cantidadDeHorasTrabajadas) {
        if (cantidadDeHorasTrabajadas <= 0) {
            throw new CantidadHorasInvalidasException("Cantidad de horas ingresadas invalida");
        }
    }

    public void verificarEntradaEstado(TipoEstado estadoNuevo) {
        if (!((estadoNuevo.equals(TipoEstado.BORRADOR)) || (estadoNuevo.equals(TipoEstado.VALIDACION_PENDIENTE)) || (estadoNuevo.equals(TipoEstado.APROBADO)) || (estadoNuevo.equals(TipoEstado.DESAPROBADO)))) {
            throw new EstadoInvalidoException("Estado invalido");
        }
    }

    public void verificarSiYaEstaAprobado(TipoEstado estado) {
        if ( estado.equals(TipoEstado.APROBADO) ) {
            throw new NoSePuedeModificarUnParteAprobadoException("No se puede modificar un parte ya aprobado");
        }
    }

    public void verificarSiYaPasoUnaSemana(Date fechaDeTareaACargar) {
        Date aWeekAgo = calcularSemanaAnterior();
        if (fechaDeTareaACargar.before(aWeekAgo)) {
            throw new LimiteDeCargaSemanaException("No se puede cargar una tarea con una semana de antiguedad");
        }
    }

    public void validateTasks(TareaDelParteDeHora[] tareasDelParteDeHoras) {
        for ( TareaDelParteDeHora tareaDelParteDeHora: tareasDelParteDeHoras) {
            validarCantidadDeHorasTrabajadas(tareaDelParteDeHora.getCantidadDeHorasTrabajadas());
            verificarEntradaEstado(tareaDelParteDeHora.getEstado());
            verificarSiYaPasoUnaSemana(tareaDelParteDeHora.getFechaDeLaTareaACargar());
        }
    }

    public void saveAll(TareaDelParteDeHora[] tareasDelParteDeHoras, Long parteDeHorasID) {
        for (TareaDelParteDeHora tareaDelParteDeHora: tareasDelParteDeHoras) {
            tareaDelParteDeHora.setParteDeHoraId(parteDeHorasID);
            guardar(tareaDelParteDeHora);
        }
    }

    public void existsById(Long tareaDelParteDeHoraId) {
        if (!tareaDelParteDeHorasRepository.existsById(tareaDelParteDeHoraId)) {
            throw new TareaNoEncontradaException("La tarea no fue encontrada");
        }
    }

    public TareaDelParteDeHora getTareaByID(Long tareaDelParteDeHoraId) {
        return tareaDelParteDeHorasRepository.findByTareaDelParteDeHoraId(tareaDelParteDeHoraId);
    }

    public Collection<TareaDelParteDeHora> getTareasByParteDeHoraId(Long parteDeHorasId) {
        return tareaDelParteDeHorasRepository.findByParteDeHoraId(parteDeHorasId);
    }
    public void modificarCantHoras(Long tareaDelParteDeHoraId, int horasNuevas) {
        existsById(tareaDelParteDeHoraId);
        validarCantidadDeHorasTrabajadas(horasNuevas);
        TareaDelParteDeHora tareaDelParteDeHoras = getTareaByID(tareaDelParteDeHoraId);
        verificarSiYaEstaAprobado(tareaDelParteDeHoras.getEstado());
        tareaDelParteDeHoras.setCantidadDeHorasTrabajadas(horasNuevas);
        guardar(tareaDelParteDeHoras);
    }

    public void modificarEstado(Long tareaDelParteDeHoraId, TipoEstado estadoNuevo) {
        existsById(tareaDelParteDeHoraId);
        verificarEntradaEstado(estadoNuevo);
        TareaDelParteDeHora tareaDelParteDeHoras = getTareaByID(tareaDelParteDeHoraId);
        verificarSiYaEstaAprobado(tareaDelParteDeHoras.getEstado());
        tareaDelParteDeHoras.setEstado(estadoNuevo);
        guardar(tareaDelParteDeHoras);
    }

    public int obtenerCantidadDeHorasTotalesDeUnaTarea (Collection<TareaDelParteDeHora> tareasDelParteDeHoras, String tareaId) {
        int horasTotales = 0;
        for (TareaDelParteDeHora tareaDelParteDeHora : tareasDelParteDeHoras) {
            if (tareaDelParteDeHora.getTareaId().equals(tareaId)) {
                horasTotales += tareaDelParteDeHora.getCantidadDeHorasTrabajadas();
            }
        }
        return horasTotales;
    }

    public Collection<TareaDelParteDeHora> obtenerTareasPorEstado (TipoEstado estado) {
        return tareaDelParteDeHorasRepository.findTareaDelParteDeHoraByEstado(estado);
    }

    public Collection<TareaDelParteDeHora> obtenerTareasPorProyectoId(String proyectoId, TipoEstado estado) {
        return tareaDelParteDeHorasRepository.findTareaDelParteDeHoraByProyectoIdAndEstado(proyectoId, estado);
    }


    public Collection<TareaDelParteDeHora> obtenerTareasEntreFechas(Collection<TareaDelParteDeHora> tareasPorProyecto, Date fechaInicio, Date fechaFin) {
        Collection<TareaDelParteDeHora> tareasADevolver = new ArrayList<>();
        for (TareaDelParteDeHora tareaDelParteDeHora : tareasPorProyecto) {
            if ((tareaDelParteDeHora.getFechaDeLaTareaACargar()).after(fechaInicio) && tareaDelParteDeHora.getFechaDeLaTareaACargar().before(fechaFin)) {
                tareasADevolver.add(tareaDelParteDeHora);
            }
        }
        return tareasADevolver;
    }

}
