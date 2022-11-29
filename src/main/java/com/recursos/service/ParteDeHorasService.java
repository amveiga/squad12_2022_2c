package com.recursos.service;

import com.recursos.exceptions.CargaInvalidaException;
import com.recursos.exceptions.EstadoInvalidoException;
import com.recursos.exceptions.NoSePuedeModificarUnParteAprobadoException;
import com.recursos.exceptions.ParteDeHorasNoEncontradoException;
import com.recursos.model.ParteDeHoras;
import com.recursos.model.TareaDelParteDeHora;
import com.recursos.repository.ParteDeHorasRepository;
import org.h2.store.FileLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ParteDeHorasService {

    @Autowired
    private ParteDeHorasRepository parteDeHorasRepository;


    public ParteDeHoras createParteDeHoras (Long legajo) {
        ParteDeHoras parteDeHoras = new ParteDeHoras(legajo);
        return parteDeHorasRepository.save(parteDeHoras);
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
