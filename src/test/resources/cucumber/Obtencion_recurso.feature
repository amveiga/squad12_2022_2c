Feature: Obtencion de recurso

  Scenario: Obtencion de recurso por legajo exitosa
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    When lo busco por legajo 107587
    Then se retorna ese recurso

  Scenario: Obtencion de recurso por nombre y apellido exitosa
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    When lo busco por nombre "Pedro y apellido "Gallino"
    Then se retorna ese recurso
