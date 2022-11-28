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
    @Given("^un recurso de nombre \"([^\"]*)\", apellido \"([^\"]*)\" y legajo (\\d+)$")
    public void hay_un_recurso_de_nombre_apellido_y_legajo(String nombre, String apellido, Long legajo) {
        recurso = crearRecurso(Long.valueOf(legajo), String.valueOf(nombre), String.valueOf(apellido));
    }

    @When("^lo busco por legajo (\\d+)$")
    public void cuando_lo_busco_por_legajo(Long legajo) {
        recurso_buscado = getRecursoLegajo(Long.valueOf(legajo));
    }

    @Then("^se retorna ese recurso$")
    public void se_retorna_ese_recurso() {
        assertEquals(recurso.getLegajo(), recurso_buscado.get().getLegajo());
    }

    @Given("^un recurso con un nombre, apellido y legajo$")
    public void unRecursoConUnNombreApellidoYLegajo() {
    }

    @When("^lo busco por nombre y apellido$")
    public void loBuscoPorNombreYApellido() {
    }
}
