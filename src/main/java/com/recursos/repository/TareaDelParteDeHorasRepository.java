package com.recursos.repository;

import com.recursos.model.TareaDelParteDeHora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface TareaDelParteDeHorasRepository extends CrudRepository<TareaDelParteDeHora, Long> {

    TareaDelParteDeHora findByTareaDelParteDeHoraId(Long tareaDelParteDeHoraId);

    Collection<TareaDelParteDeHora> findByParteDeHoraId(Long parteDeHorasId);

    Collection<TareaDelParteDeHora> findTareaDelParteDeHoraByEstadoIgnoreCase(String estado);

    Collection<TareaDelParteDeHora> findTareaDelParteDeHoraByProyectoIdAndEstado(String proyectoId, String estado);

    @Override
    List<TareaDelParteDeHora> findAll();
}
