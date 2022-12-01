package com.recursos.integration.cucumber;

import com.recursos.ModuloRecursosApp;
import com.recursos.model.Recurso;
import com.recursos.model.TareaDelParteDeHora;
import com.recursos.service.RecursoService;
import com.recursos.service.TareaDelParteDeHorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@ContextConfiguration(classes = ModuloRecursosApp.class)
@WebAppConfiguration

public class TareaParteDeHorasIntegrationServiceTest {

    @Autowired
    TareaDelParteDeHorasService tareaService;

    TareaDelParteDeHora crearTarea(int horas, String estado, Date date) {
        TareaDelParteDeHora tarea = new TareaDelParteDeHora();
        tarea.setCantidadDeHorasTrabajadas(horas);
        tarea.setEstado(estado);
        tarea.setFechaDeLaTareaACargar(date);
        TareaDelParteDeHora[] tareasDelParteDeHoras = {tarea};
        tareaService.validateTasks(tareasDelParteDeHoras);
        tareaService.saveAll(tareasDelParteDeHoras, 0L);
        return tarea;
    }

    void modificarHoras(int horas, Long id) {
        tareaService.modificarCantHoras(id, horas);
    }
}
