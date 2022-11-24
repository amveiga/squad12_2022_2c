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

    public Collection<ParteDeHoras> getTransactions() { return parteDeHorasRepository.findAll(); }

    public Optional<ParteDeHoras> findById(Long transactionId) { return parteDeHorasRepository.findById(transactionId); }
    public Collection<ParteDeHoras> findByCbu(Long cbu) {
        Collection<ParteDeHoras> cbuParteDeHoras = new ArrayList<>();

        for (ParteDeHoras parteDeHoras : parteDeHorasRepository.findAll()) {
            if (parteDeHoras.getCbu().equals(cbu)) {
                cbuParteDeHoras.add(parteDeHoras);
            }
        }

        return cbuParteDeHoras;
    }

    public void save(ParteDeHoras parteDeHoras) { parteDeHorasRepository.save(parteDeHoras); }

    public void deleteById(Long cbu) { parteDeHorasRepository.deleteById(cbu); }
}
