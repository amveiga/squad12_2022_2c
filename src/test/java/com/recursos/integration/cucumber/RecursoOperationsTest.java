package com.recursos.integration.cucumber;

import com.recursos.exceptions.LegajoNoEncontradoException;
import com.recursos.exceptions.CargaInvalidaException;
import com.recursos.model.Recurso;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecursoOperationsTest extends RecursoIntegrationServiceTest {

    private Recurso recurso;
    private Optional<Recurso> recurso_buscado;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }
    @Given("^hay un recurso de nombre \"([^\"]*)\", apellido \"([^\"]*)\" y legajo (\\d+)$")
    public void hay_un_recurso_de_nombre_apellido_y_legajo(String arg1, String arg2, int arg3) {
        // Write code here that turns the phrase above into concrete actions
        recurso = crearRecurso((long) arg3, arg1, arg2);
    }

    @When("^cuando lo busco por legajo (\\d+)$")
    public void cuando_lo_busco_por_legajo(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        recurso_buscado = getRecursoLegajo((long) arg1);
    }

    @Then("^se retorna ese recurso$")
    public void se_retorna_ese_recurso() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(recurso, recurso_buscado);
    }
}
