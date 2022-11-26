package com.recursos.service;

import com.recursos.exceptions.CargaInvalidaException;
import com.recursos.exceptions.EstadoInvalidoException;
import com.recursos.exceptions.NoSePuedeModificarUnParteAprobadoException;
import com.recursos.exceptions.ParteDeHorasNoEncontradoException;
import com.recursos.model.ParteDeHoras;
import com.recursos.repository.ParteDeHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ParteDeHorasService {

    @Autowired
    private ParteDeHorasRepository parteDeHorasRepository;


    public Date calcularSemanaAnterior() {
        Date todayDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(todayDate);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        return (c.getTime());
    }

    public void verificarEstado(String estadoAnterior, String estadoNuevo) {
        if ( estadoAnterior.equalsIgnoreCase("APROBADO") ) {
            throw new NoSePuedeModificarUnParteAprobadoException("No se puede cambiar el estado de un parte ya aprobado");
        } else if ((estadoNuevo.equalsIgnoreCase("BORRADOR")) || (estadoNuevo.equalsIgnoreCase("VALIDACION_PENDIENTE")) || (estadoNuevo.equalsIgnoreCase("APROBADO")) || (estadoNuevo.equalsIgnoreCase("DESAPROBADO"))) {
            return;
        } else {
            throw new EstadoInvalidoException("Estado invalido");
        }
    }


    public Optional<ParteDeHoras> createParteDeHoras (ParteDeHoras parteDeHoras) {
        if (parteDeHoras.getCantidadDeHorasTrabajadas() <= 0) {
            throw new CargaInvalidaException("No se pueden cargar 0 o menos horas de trabajo");
        }

        Date aWeekAgo = calcularSemanaAnterior();

        if (parteDeHoras.getFechaDeLaTareaACargar().before(aWeekAgo)){
             throw new CargaInvalidaException("No se pueden cargar horas de trabajo previas a 1 semana");
        }

        return Optional.of(parteDeHorasRepository.save(parteDeHoras));
    }

    public Collection<ParteDeHoras> getParteDeHoras() { return parteDeHorasRepository.findAll(); }

    public Optional<Collection<ParteDeHoras>> getPartesByLegajo(Long legajo) {
        List<ParteDeHoras> parteDeHoras = parteDeHorasRepository.findParteDeHorasByLegajoEmpleado(legajo);

        if (parteDeHoras.isEmpty()) {
            return Optional.empty();
        } else return Optional.of(parteDeHoras);
    }


    public ParteDeHoras getPartesByID(Long parteDeHorasID) {
        ParteDeHoras parteDeHoras = parteDeHorasRepository.findParteDeHorasByParteDeHorasID(parteDeHorasID);
        if (parteDeHoras == null) {
            throw new ParteDeHorasNoEncontradoException("No se encontró el parte de horas");
        } else return parteDeHoras;
    }

    public void save(ParteDeHoras parteDeHoras) {
        parteDeHorasRepository.save(parteDeHoras);
    }

    public void deleteById(Long Id) {
        parteDeHorasRepository.deleteById(Id);
    }
}
