package com.recursos.integration.cucumber;

import com.recursos.ModuloRecursosApp;
import com.recursos.model.Recurso;
import com.recursos.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = ModuloRecursosApp.class)
@WebAppConfiguration
public class AccountIntegrationServiceTest {

    @Autowired
    RecursoService recursoService;

    Recurso createAccount(Double balance) {
        return recursoService.createAccount(new Recurso(balance));
    }

    Recurso withdraw(Recurso recurso, Double sum) {
        return recursoService.withdraw(recurso.getCbu(), sum);
    }

    Recurso deposit(Recurso recurso, Double sum) {
        return recursoService.deposit(recurso.getCbu(), sum);
    }

}
