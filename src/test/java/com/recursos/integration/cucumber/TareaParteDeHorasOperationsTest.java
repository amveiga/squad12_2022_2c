package com.recursos.integration.cucumber;

import com.recursos.exceptions.*;
import com.recursos.model.TareaDelParteDeHora;
import com.recursos.model.TipoEstado;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TareaParteDeHorasOperationsTest extends TareaParteDeHorasIntegrationServiceTest {

    TareaDelParteDeHora tarea;

    Long tareaID;
    Exception exception;
    String proyectoId;
    Date fecha_inicio;
    Date fecha_fin;

    int calculo;

    Long parteId;
    Collection<TareaDelParteDeHora> entreFechas;
    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^una tarea de id \"([^\"]*)\" con (\\d+) horas y estado \"([^\"]*)\" el \"([^\"]*)\" de proyecto \"([^\"]*)\"$")
    public void unaTareaDeIdConHorasYEstadoElDeProyecto(String tareaid, int horas, TipoEstado estado, String fecha, String proyecto)
            throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        proyectoId = proyecto;
        try {
            tarea = crearTarea(horas, tareaid, estado, date, proyecto);
        } catch(CantidadHorasInvalidasException
                | EstadoInvalidoException
                | LimiteDeCargaSemanaException
                | LegajoNoEncontradoException e) {
            exception = e;
        }
    }

    @When("^modifico las horas trabajadas por (\\d+)$")
    public void modificoLasHorasTrabajadasPor(int horasNuevas) {
        try {
            tareaID = tarea.getTareaDelParteDeHoraId();
            modificarHoras(horasNuevas, tareaID);
        } catch(CantidadHorasInvalidasException | NoSePuedeModificarUnParteAprobadoException e) {
            exception = e;
        }
    }

    @Then("^no es posible modificarla$")
    public void noEsPosibleModificarla() {
        assertNotNull(exception);
    }

    @Then("^no es posible cargarla$")
    public void noEsPosibleCargarla() {
        assertNotNull(exception);
    }

    @When("^la cargo$")
    public void laCargo() {
    }

//    @Given("^una tarea con -(\\d+) horas y estado \"([^\"]*)\"$")
//    public void unaTareaConHorasNegativasYEstado(int horas, String estado) {
//        try {
//            tarea = crearTarea(horas, estado);
//        } catch(Exception e) {
//            exception = e;
//        }
//    }

    @Then("^se carga con (\\d+) horas y estado \"([^\"]*)\"$")
    public void seCargaConHorasYEstado(int horas, TipoEstado estado) {
        assertEquals(horas, tarea.getCantidadDeHorasTrabajadas());
        assertEquals(estado, tarea.getEstado());
    }

    @And("^la tarea queda con (\\d+) horas$")
    public void laTareaQuedaConHoras(int horas) {
        int horasTarea = tarea.getCantidadDeHorasTrabajadas();
        assertEquals(horas, horasTarea);
    }
    @Then("^la tarea queda con (\\d+) horas trabajadas$")
    public void laTareaQuedaConHorasTrabajadas(int horas) {
        TareaDelParteDeHora tareaBuscada = getTareaById(tareaID);
        int horasTrabajadas = tareaBuscada.getCantidadDeHorasTrabajadas();
        assertEquals(horas, horasTrabajadas);
    }
    @Then("^la tarea no se modifica$")
    public void laTareaNoSeModifica() {
        assertNotNull(exception);
    }

    @When("^busco tareas entre el \"([^\"]*)\" y el \"([^\"]*)\" en estado \"([^\"]*)\"$")
    public void buscoTareasEntreElYElEnEstado(String inicio, String fin, TipoEstado estado) throws ParseException {
        fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").parse(inicio);
        fecha_fin = new SimpleDateFormat("dd/MM/yyyy").parse(fin);
        Collection<TareaDelParteDeHora> tareas = getTareasDeProyecto(proyectoId, estado);
        entreFechas = getEntreFechas(TareaParteDeHorasOperationsTest.this.fecha_inicio, fecha_fin, tareas);
    }

    @Then("^retorna un listado de tareas entre las fechas correspondientes$")
    public void retornaUnListadoDeTareasEntreLasFechasCorrespondientes() {
        Iterator<TareaDelParteDeHora> it = entreFechas.iterator();
        while(it.hasNext()) {
            TareaDelParteDeHora tarea_listada = it.next();
            boolean validacion = false;
            if ((tarea_listada.getFechaDeLaTareaACargar()).after(fecha_inicio) &&
                    tarea_listada.getFechaDeLaTareaACargar().before(fecha_fin)) {
                validacion = true;
            }
            assertEquals(true, validacion);
        }
    }
//    @When("^calculo la cantidad de horas de \"([^\"]*)\" con estado \"([^\"]*)\"$")
//    public void calculoLaCantidadDeHorasDeConEstado(String tareaId, String estado) {
//    //    calculo = calcularHorasPorIdyEstado(tareaId, estado);
//    }
//    @Then("^retorna la suma total de (\\d+)$")
//    public void retornaLaSumaTotalDe(int suma) {
//        assertEquals(suma, calculo);
//    }
//    @When("^calculo la cantidad de horas de \"([^\"]*)\" con estado \"([^\"]*)\" de proyecto \"([^\"]*)\"$")
//    public void calculoLaCantidadDeHorasDeConEstadoDeProyecto(String arg0, String arg1, String arg2) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
