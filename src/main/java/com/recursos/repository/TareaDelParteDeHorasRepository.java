package com.recursos.repository;

import com.recursos.model.TareaDelParteDeHora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface TareaDelParteDeHorasRepository extends CrudRepository<TareaDelParteDeHora, Long> {

    TareaDelParteDeHora findByTareaDelParteDeHoraId(Long tareaDelParteDeHoraId);

    Collection<TareaDelParteDeHora> findByParteDeHoraId(Long parteDeHorasId);

    @Override
    List<TareaDelParteDeHora> findAll();
}
