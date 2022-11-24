package com.recursos.service;

import com.recursos.model.ParteDeHoras;
import com.recursos.repository.ParteDeHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ParteDeHorasService {

    @Autowired
    private ParteDeHorasRepository parteDeHorasRepository;

    public ParteDeHoras createTransaction(ParteDeHoras parteDeHoras) {

        return parteDeHorasRepository.save(parteDeHoras);
    }

    public Collection<ParteDeHoras> getParteDeHoras() { return parteDeHorasRepository.findAll(); }

    public void save(ParteDeHoras parteDeHoras) { parteDeHorasRepository.save(parteDeHoras); }

    public void deleteById(Long Id) { parteDeHorasRepository.deleteById(Id); }
}
