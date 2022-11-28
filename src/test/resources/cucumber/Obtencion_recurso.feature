Feature: Obtencion de recurso

  Scenario: Obtencion de recurso por legajo exitosa
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    When lo busco por legajo 107587
    Then se retorna ese recurso

  Scenario: Obtencion de listado de recursos por nombre exitosa
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    And un recurso de nombre "Pedro", apellido "Gayino" y legajo 107588
    When busco por nombre "Pedro"
    Then se retorna un listado con los recursos de nombre "Pedro"

  Scenario: Obtencion de listado de recursos por apellido exitosa
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    And un recurso de nombre "Pablo", apellido "Gallino" y legajo 107588
    When busco por apellido "Gallino"
    Then se retorna un listado con los recursos de apellido "Gallino"

  Scenario: Obtencion de listado de recursos por nombre y apellido exitosa
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    And un recurso de nombre "Pedro", apellido "Gallino" y legajo 107588
    When busco por nombre "Pedro" y apellido "Gallino"
    Then se retorna un listado con los recursos de nombre "Pedro" y apellido "Gallino"