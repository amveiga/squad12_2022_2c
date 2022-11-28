package com.recursos.repository;

import com.recursos.model.Recurso;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecursoRepository extends CrudRepository<Recurso, Long> {

    List<Recurso> findRecursosByApellidoAndNombre(String Apellido, String Nombre);
    List<Recurso> findRecursosByNombre(String Nombre);
    List<Recurso> findRecursosByApellido(String Apellido);

    @Override
    List<Recurso> findAll();

}
