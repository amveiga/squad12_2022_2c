package com.recursos.repository;

import com.recursos.model.TareaDelParteDeHora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface TareaDelParteDeHorasRepository extends CrudRepository<TareaDelParteDeHora, Long> {

    TareaDelParteDeHora findByTareaDelParteDeHoraId(Long tareaDelParteDeHoraId);

    Collection<TareaDelParteDeHora> findByParteDeHoraId(Long parteDeHorasId);

    Collection<TareaDelParteDeHora> findTareaDelParteDeHoraByEstadoIgnoreCase(String estado);

    Collection<TareaDelParteDeHora> findTareaDelParteDeHoraByProyectoIdAndEstado(String proyectoId, String estado);

    //Collection<TareaDelParteDeHora> findTareaDelParteDeHoraByFechaDeLaTareaACargarBetween(Date fechaInicio, Date fechaFin);


    @Override
    List<TareaDelParteDeHora> findAll();
}
