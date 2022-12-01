Feature: Obtencion de Parte de horas

  Scenario: Obtencion de parte de horas por legajo exitosa
    Given un parte de horas del legajo 107587
    When busco parte por legajo 107587
    Then se retorna un listado con los partes de legajo 107587

  Scenario: Obtencion de parte de horas por legajo exitosa
    Given un parte de horas del legajo 107587
    And un parte de horas del legajo 107587
    And un parte de horas del legajo 107588
    When busco parte por legajo 107587
    Then se retorna un listado con los partes de legajo 107587

  Scenario: Obtencion de parte de horas por legajo fallida
    Given un parte de horas del legajo 107587
    And un parte de horas del legajo 107588
    And un parte de horas del legajo 107589
    When busco parte por legajo 107590
    Then no se encuentra el parte

    #da 10 porque se tienen en cuenta los 7 de los test anteriores
  Scenario: Obtencion de todos los partes de horas
    Given un parte de horas del legajo 107587
    And un parte de horas del legajo 107588
    And un parte de horas del legajo 107589
    When busco todos los partes
    Then se retorna un listado con los 10 partes

  Scenario: Obtencion de parte de horas por ID exitoso
    Given un parte de horas del legajo 123456
    When busco parte por ID
    Then se retorna el parte correspondiente

  Scenario: Obtencion de parte de horas por ID fallido
    Given un parte de horas del legajo 107587
    When busco parte por ID erróneo
    Then no se retorna el parte correspondiente