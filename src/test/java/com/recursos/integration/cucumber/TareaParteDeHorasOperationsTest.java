package com.recursos.integration.cucumber;

import com.recursos.exceptions.LegajoNoEncontradoException;
import com.recursos.model.Recurso;
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


    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }
}
