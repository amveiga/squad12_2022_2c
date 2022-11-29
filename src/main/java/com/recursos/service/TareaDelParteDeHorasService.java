package com.recursos.service;

import com.recursos.model.TareaDelParteDeHora;
import com.recursos.repository.TareaDelParteDeHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class TareaDelParteDeHorasService {

    @Autowired
    private TareaDelParteDeHorasRepository tareaDelParteDeHorasRepository;

    public TareaDelParteDeHora save(TareaDelParteDeHora tareaDelParteDeHora) {
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

    public boolean validarCantidadDeHorasTrabajadas (int cantidadDeHorasTrabajadas) {
        return cantidadDeHorasTrabajadas > 0;
    }


    public boolean verificarEntradaEstado(String estadoNuevo) {
        //throw new EstadoInvalidoException("Estado invalido");
        return (estadoNuevo.equalsIgnoreCase("BORRADOR")) || (estadoNuevo.equalsIgnoreCase("VALIDACION_PENDIENTE")) || (estadoNuevo.equalsIgnoreCase("APROBADO")) || (estadoNuevo.equalsIgnoreCase("DESAPROBADO"));
    }

    public boolean verificarSiYaEstaAprobado(String estado) {
        if ( estado.equalsIgnoreCase("APROBADO") ) {
            return true;
            //throw new NoSePuedeModificarUnParteAprobadoException("No se puede modificar un parte ya aprobado");
        }
        return false;
    }

    public boolean validateTasks(TareaDelParteDeHora[] tareasDelParteDeHoras) {
        Date aWeekAgo = calcularSemanaAnterior();
        for ( TareaDelParteDeHora tareaDelParteDeHora: tareasDelParteDeHoras) {
            if (tareaDelParteDeHora.getFechaDeLaTareaACargar().before(aWeekAgo) ||
                !validarCantidadDeHorasTrabajadas(tareaDelParteDeHora.getCantidadDeHorasTrabajadas()) ||
                !verificarEntradaEstado(tareaDelParteDeHora.getEstado())) {
                return false;
            }
        }
        return true;
    }

    public void saveAll(TareaDelParteDeHora[] tareasDelParteDeHoras, Long parteDeHorasID) {
        for (TareaDelParteDeHora tareaDelParteDeHora: tareasDelParteDeHoras) {
            tareaDelParteDeHora.setParteDeHoraId(parteDeHorasID);
            tareaDelParteDeHorasRepository.save(tareaDelParteDeHora);
        }
    }

    public boolean existsById(Long tareaDelParteDeHoraId) {
        return tareaDelParteDeHorasRepository.existsById(tareaDelParteDeHoraId);
    }

    public TareaDelParteDeHora getTareaByID(Long tareaDelParteDeHoraId) {
        return tareaDelParteDeHorasRepository.findByTareaDelParteDeHoraId(tareaDelParteDeHoraId);
    }
}
