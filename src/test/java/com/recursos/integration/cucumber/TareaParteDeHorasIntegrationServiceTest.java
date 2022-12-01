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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@ContextConfiguration(classes = ModuloRecursosApp.class)
@WebAppConfiguration

public class TareaParteDeHorasIntegrationServiceTest {

    @Autowired
    TareaDelParteDeHorasService tareaService;


    TareaDelParteDeHora crearTarea(int horas, String tareaId, String estado, Date date, String proyectoId) {
        TareaDelParteDeHora tarea = new TareaDelParteDeHora();
        tarea.setCantidadDeHorasTrabajadas(horas);
        tarea.setTareaId(tareaId);
        tarea.setEstado(estado);
        tarea.setFechaDeLaTareaACargar(date);
        tarea.setProyectoId(proyectoId);
        TareaDelParteDeHora[] tareasDelParteDeHoras = {tarea};
        tareaService.validateTasks(tareasDelParteDeHoras);
        tareaService.saveAll(tareasDelParteDeHoras, 0L);
        return tarea;
    }

    void modificarHoras(int horas, Long id) {
        tareaService.modificarCantHoras(id, horas);
    }

    TareaDelParteDeHora getTareaById (Long id) {
        return tareaService.getTareaByID(id);
    }

    Collection<TareaDelParteDeHora> getTareasDeProyecto(String proyectoId, String estado) {
        return  tareaService.obtenerTareasPorProyectoId(proyectoId, estado);
    }

//    int calcularHorasPorIdyEstado(String estado, String tareaId, String proyectoId) {
//        return tareaService.obtenerCantidadDeHorasTotalesDeUnaTarea(tareaService.obtenerTareasPorProyectoId(proyectoId, estado), tareaId);
//    }
    Collection<TareaDelParteDeHora> getEntreFechas(Date inicio, Date fin, Collection<TareaDelParteDeHora> tareas) {
        return tareaService.obtenerTareasEntreFechas(tareas, inicio, fin);
    }

}
