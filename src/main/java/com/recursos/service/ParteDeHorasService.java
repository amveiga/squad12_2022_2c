package com.recursos.service;

import com.recursos.model.ParteDeHoras;
import com.recursos.model.Recurso;
import com.recursos.repository.ParteDeHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ParteDeHorasService {

    @Autowired
    private ParteDeHorasRepository parteDeHorasRepository;

    public ParteDeHoras createParteDeHoras (ParteDeHoras parteDeHoras) {
        return parteDeHorasRepository.save(parteDeHoras);
    }

    public Collection<ParteDeHoras> getParteDeHoras() { return parteDeHorasRepository.findAll(); }

    public Optional<Collection<ParteDeHoras>> getPartesByLegajo(Long legajo) {
        Collection<ParteDeHoras> parteDeHoras = new ArrayList<>();

        for (ParteDeHoras parteDeHora: parteDeHorasRepository.findAll()) {
            if (parteDeHora.getLegajoEmpleado().equals(legajo)) {
                parteDeHoras.add(parteDeHora);
            }
        }
        if (parteDeHoras.size() == 0) {
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
