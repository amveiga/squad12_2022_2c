package com.recursos.integration.cucumber;

import com.recursos.ModuloRecursosApp;
import com.recursos.model.Recurso;
import com.recursos.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.Optional;

@ContextConfiguration(classes = ModuloRecursosApp.class)
@WebAppConfiguration
public class RecursoIntegrationServiceTest {
    @Autowired
    RecursoService recursoService;
    Recurso crearRecurso(Long legajo,
                         String nombre,
                         String apellido) {
        Recurso recurso = new Recurso();
        //TODO: tal vez sirva un constructor de recurso
        recurso.setLegajo(legajo);
        recurso.setNombre(nombre);
        recurso.setApellido((apellido));
        return recursoService.crearRecurso(recurso);
    }

    Optional<Recurso> getRecursoLegajo(Long legajo) {
        return recursoService.findById(legajo);
    }

//    Optional<Collection<Recurso>> getRecursoApellido(String nombre, String apellido) {
//        return recursoService.findByNameAndFamilyName(nombre, apellido);
//    }
}
