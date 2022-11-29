package com.recursos.repository;

import com.recursos.model.TareaDelParteDeHora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface TareaDelParteDeHorasRepository extends CrudRepository<TareaDelParteDeHora, Long> {

    TareaDelParteDeHora findByTareaDelParteDeHoraId(Long tareaDelParteDeHoraId);

    @Override
    List<TareaDelParteDeHora> findAll();
}
