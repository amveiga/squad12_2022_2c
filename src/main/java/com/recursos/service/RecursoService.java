package com.recursos.service;

import com.recursos.model.Recurso;
import com.recursos.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Collection<Recurso>> findByNameAndFamilyName(String nombre, String apellido) {
        Collection<Recurso> recursos = recursoRepository.findRecursosByApellidoAndNombre(apellido,nombre);
        if (recursos.isEmpty()) {
            return Optional.empty();
        } else return Optional.of(recursos);
    }

    public Optional<Collection<Recurso>> findByFirstName(String nombre) {
        Collection<Recurso> recursos = recursoRepository.findRecursosByNombre(nombre);
        if (recursos.isEmpty()) {
            return Optional.empty();
        } else return Optional.of(recursos);
    }

    public Optional<Collection<Recurso>> findByFamilyName(String apellido) {
        Collection<Recurso> recursos = recursoRepository.findRecursosByApellido(apellido);
        if (recursos.isEmpty()) {
            return Optional.empty();
        } else return Optional.of(recursos);
    }
}
