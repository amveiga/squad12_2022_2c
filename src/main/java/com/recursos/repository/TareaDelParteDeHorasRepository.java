package com.recursos.repository;

import com.recursos.model.ParteDeHoras;
import com.recursos.model.TareaDelParteDeHora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TareaDelParteDeHorasRepository extends CrudRepository<TareaDelParteDeHora, Long> {

    @Override
    List<TareaDelParteDeHora> findAll();
}
