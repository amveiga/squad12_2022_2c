package com.recursos.service;

import com.recursos.exceptions.CargaInvalidaException;
import com.recursos.model.ParteDeHoras;
import com.recursos.model.Recurso;
import com.recursos.repository.ParteDeHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalTime;
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
        Date aWeekAgo = c.getTime();
        return aWeekAgo;
    }

    public Optional<ParteDeHoras> createParteDeHoras (ParteDeHoras parteDeHoras) {
        Date aWeekAgo = calcularSemanaAnterior();

        if (parteDeHoras.getFechaDeLaTareaACargar().before(aWeekAgo)){
            // No se pueden agregar horas trabajadas pasada 1 semana
            //throw new CargaInvalidaException("No se pueden cargar horas de trabajo previas a 1 semana");
            return Optional.empty();
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

    public void save(ParteDeHoras parteDeHoras) {
        parteDeHorasRepository.save(parteDeHoras);
    }

    public void deleteById(Long Id) {
        parteDeHorasRepository.deleteById(Id);
    }
}
