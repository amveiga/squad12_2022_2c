package com.recursos.integration.cucumber;

import com.recursos.exceptions.LegajoNoEncontradoException;
import com.recursos.exceptions.CargaInvalidaException;
import com.recursos.model.Recurso;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecursoOperationsTest extends RecursoIntegrationServiceTest {

    private Recurso recurso;
    private Optional<Recurso> recurso_buscado;
    private Optional<Collection<Recurso>> lista_nombre_apellido, lista_nombre, lista_apellido;
    private Exception exception;
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
        try {
            recurso_buscado = getRecursoLegajo(Long.valueOf(legajo));
        } catch (LegajoNoEncontradoException e) {
            exception = e;
        }
    }

    @Then("^se retorna ese recurso$")
    public void se_retorna_ese_recurso() {
        assertEquals(recurso.getLegajo(), recurso_buscado.get().getLegajo());
        assertEquals(recurso.getApellido(), recurso_buscado.get().getApellido());
        assertEquals(recurso.getNombre(), recurso_buscado.get().getNombre());
    }

    @When("^busco por nombre \"([^\"]*)\" y apellido \"([^\"]*)\"$")
    public void loBuscoPorNombreYApellido(String nombre, String apellido) {
        try {
            lista_nombre_apellido = getRecursoNombreApellido(nombre, apellido);
        } catch (LegajoNoEncontradoException e) {
            exception = e;
        }
    }
    @Then("^se retorna un listado con los recursos de nombre \"([^\"]*)\" y apellido \"([^\"]*)\"$")
    public void seRetornaUnListadoConLosRecursosDeNombreYApellido(String nombre, String apellido) {
        Collection<Recurso> listado = lista_nombre_apellido.get();
        Iterator<Recurso> it = listado.iterator();
        while(it.hasNext()) {
            Recurso recurso_listado = it.next();
            assertEquals(nombre, recurso_listado.getNombre());
            assertEquals(apellido, recurso_listado.getApellido());
        }
    }

    @When("^busco por nombre \"([^\"]*)\"$")
    public void buscoPorNombre(String nombre)  {
        try {
            lista_nombre = getRecursoNombre(nombre);
        } catch (LegajoNoEncontradoException e) {
            exception = e;
        }
    }

    @Then("^se retorna un listado con los recursos de nombre \"([^\"]*)\"$")
    public void seRetornaUnListadoConLosRecursosDeNombre(String nombre) {
        Collection<Recurso> listado = lista_nombre.get();
        Iterator<Recurso> it = listado.iterator();
        while(it.hasNext()) {
            Recurso recurso_listado = it.next();
            assertEquals(nombre, recurso_listado.getNombre());
        }
    }

    @When("^busco por apellido \"([^\"]*)\"$")
    public void buscoPorApellido(String apellido) {
        try {
            lista_apellido = getRecursoApellido(apellido);
        } catch (LegajoNoEncontradoException e) {
            exception = e;
        }
    }

    @Then("^se retorna un listado con los recursos de apellido \"([^\"]*)\"$")
    public void seRetornaUnListadoConLosRecursosDeApellido(String apellido) {
        Collection<Recurso> listado = lista_apellido.get();
        Iterator<Recurso> it = listado.iterator();
        while(it.hasNext()) {
            Recurso recurso_listado = it.next();
            assertEquals(apellido, recurso_listado.getApellido());
        }
    }

    @When("^elimino por legajo (\\d+)$")
    public void loEliminoPorLegajo(Long legajo) {
        try {
            DeleteRecursoLegajo(legajo);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("^se elimina el recurso (\\d+)$")
    public void seEliminaElRecurso(Long legajo) {
        cuando_lo_busco_por_legajo(legajo);
        assertNotNull(exception);
    }
    @Then("^no se encuentra el recurso$")
    public void noSeEncuentraElRecurso() {
        assertNotNull(exception);
    }

    @Then("^no se elimina el recurso (\\d+)$")
    public void noSeEliminaElRecurso(long legajo) {
        cuando_lo_busco_por_legajo(legajo);
        assertEquals(true, recurso_buscado.isPresent());
    }
}
