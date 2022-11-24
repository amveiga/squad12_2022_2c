package com.recursos.service;

import com.recursos.model.Recurso;
import com.recursos.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public Recurso crearRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public Collection<Recurso> getRecursos() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> findById(Long legajo) {
        return recursoRepository.findById(legajo);
    }

    public void save(Recurso recurso) {
        recursoRepository.save(recurso);
    }

    public void deleteById(Long legajo) {
        recursoRepository.deleteById(legajo);
    }

    public Optional<Collection<Recurso>> findByName(String nombre, String apellido) {
        Collection<Recurso> recursos = new ArrayList<>();

        for (Recurso recurso: recursoRepository.findAll()) {
            if (recurso.getNombre().equals(nombre) && recurso.getApellido().equals(apellido)) {
                recursos.add(recurso);
            }
        }
        if (recursos.size() == 0) {
            return Optional.empty();
        } else return Optional.of(recursos);
    }
}
