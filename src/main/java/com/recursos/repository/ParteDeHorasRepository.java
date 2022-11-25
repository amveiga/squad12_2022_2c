package com.recursos.repository;

import com.recursos.model.ParteDeHoras;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ParteDeHorasRepository extends CrudRepository<ParteDeHoras, Long> {

    List<ParteDeHoras> findParteDeHorasByLegajoEmpleado(Long legajo);

    ParteDeHoras findParteDeHorasByParteDeHorasID(Long parteDeHorasID);


    @Override
    List<ParteDeHoras> findAll();

}