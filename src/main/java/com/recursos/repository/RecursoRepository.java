package com.recursos.repository;

import com.recursos.model.Recurso;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecursoRepository extends CrudRepository<Recurso, Long> {

    List<Recurso> findRecursosByApellidoAndNombre(String apellido, String nombre);
    List<Recurso> findRecursosByNombre(String nombre);
    List<Recurso> findRecursosByApellido(String apellido);

    @Override
    List<Recurso> findAll();

}
