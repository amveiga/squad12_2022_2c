package com.recursos.integration.cucumber;

import com.recursos.exceptions.ParteDeHorasNoEncontradoException;
import com.recursos.model.ParteDeHoras;
import com.recursos.model.Recurso;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParteDeHorasOperationsTest extends ParteDeHorasIntegrationServiceTest{

    ParteDeHoras parte;
    Optional<Collection<ParteDeHoras>> buscados;

    ParteDeHoras buscado;
    Exception exception;

    @Given("^un parte de horas del legajo (\\d+)$")
    public void unParteDeHorasDelLegajo(Long legajo) {
        try {
            parte = crearParte(legajo);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("^busco parte por legajo (\\d+)$")
    public void buscoPartePorLegajo(Long legajo) {
        try {
            buscados = getPartesLegajo(legajo);
        } catch (ParteDeHorasNoEncontradoException e) {
            exception = e;
        }
    }

    @Then("^se retorna un listado con los partes de legajo (\\d+)$")
    public void seRetornaUnListadoConLosPartesDeLegajo(Long legajo) {
        Collection<ParteDeHoras> listado = buscados.get();
        Iterator<ParteDeHoras> it = listado.iterator();
        while(it.hasNext()) {
            ParteDeHoras parte_listado = it.next();
            assertEquals(legajo, parte_listado.getLegajoEmpleado());
        }
    }

    @Then("^no se encuentra el parte$")
    public void noSeEncuentraElParte() {
        assertEquals(true, buscados.isEmpty());
    }

    @When("^busco todos los partes$")
    public void buscoTodosLosPartes() {
        buscados = getPartes();
    }

    @Then("^se retorna un listado con los (\\d+) partes$")
    public void seRetornaUnListadoConLosPartes(int cantidad) {
        assertEquals(cantidad, buscados.get().size());
    }

    @When("^busco parte por ID$")
    public void buscoPartePorID() {
        try {
            buscado = getParteId(parte.getParteDeHorasID());
        } catch (ParteDeHorasNoEncontradoException e) {
            exception = e;
        }
    }

    @Then("^se retorna el parte correspondiente$")
    public void seRetornaElParteCorrespondiente() {
        assertEquals(parte.getLegajoEmpleado(), buscado.getLegajoEmpleado());
        assertEquals(parte.getParteDeHorasID(), buscado.getParteDeHorasID());
    }

    @When("^busco parte por ID erróneo$")
    public void buscoPartePorIDErróneo() {
        try {
            buscado = getParteId(parte.getParteDeHorasID() + 1);
        } catch (ParteDeHorasNoEncontradoException e) {
            exception = e;
        }
    }

    @Then("^no se retorna el parte correspondiente$")
    public void noSeRetornaElParteCorrespondiente() {
        assertNotNull(exception);
    }
}
