package com.recursos.integration.cucumber;

import com.recursos.ModuloRecursosApp;
import com.recursos.model.ParteDeHoras;
import com.recursos.service.ParteDeHorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.Optional;

@ContextConfiguration(classes = ModuloRecursosApp.class)
@WebAppConfiguration
public class ParteDeHorasIntegrationServiceTest {

    @Autowired
    ParteDeHorasService parteService;

    ParteDeHoras crearParte(Long legajo) {
        return parteService.createParteDeHoras(legajo);
    }

    ParteDeHoras getParteId(Long id) {
        return parteService.getParteByID(id);
    }

    Optional<Collection<ParteDeHoras>> getPartesLegajo(Long legajo) {
        return parteService.getPartesByLegajo(legajo);
    }

    Optional<Collection<ParteDeHoras>> getPartes() {
        return Optional.ofNullable(parteService.getParteDeHoras());
    }
}
