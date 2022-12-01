package com.recursos.integration.cucumber;

import com.recursos.exceptions.LegajoNoEncontradoException;
import com.recursos.model.Recurso;
import com.recursos.model.TareaDelParteDeHora;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TareaParteDeHorasOperationsTest extends TareaParteDeHorasIntegrationServiceTest {

    TareaDelParteDeHora tarea;
    Exception exception;
    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^una tarea con (\\d+) horas y estado \"([^\"]*)\"$")
    public void unaTareaConHorasYEstado(int horas, String estado) {
        try {
            tarea = crearTarea(horas, estado);
        } catch(Exception e) {
            exception = e;
        }
    }

    @When("^modifico las horas trabajadas por (\\d+)$")
    public void modificoLasHorasTrabajadasPor(int horasNuevas) {
        try {
            Long id = tarea.getTareaDelParteDeHoraId();
            modificarHoras(horasNuevas, id);
        } catch(Exception e) {
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

    @Given("^una tarea con -(\\d+) horas y estado \"([^\"]*)\"$")
    public void unaTareaConHorasNegativasYEstado(int horas, String estado) {
        try {
            tarea = crearTarea(horas, estado);
        } catch(Exception e) {
            exception = e;
        }
    }
}
