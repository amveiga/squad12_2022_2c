package com.recursos.repository;

import com.recursos.model.Recurso;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecursoRepository extends CrudRepository<Recurso, Long> {

    Recurso findAccountByLegajo(Long legajo);

    @Override
    List<Recurso> findAll();

}
