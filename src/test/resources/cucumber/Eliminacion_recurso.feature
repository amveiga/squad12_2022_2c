Feature: Eliminacion de recurso

  Scenario: eliminacion de recurso existente por legajo exitosa
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    When elimino por legajo 107587
    Then se elimina el recurso 107587

  Scenario: eliminacion de recurso inexistente por legajo fallida
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    When elimino por legajo 000000
    Then no se elimina el recurso 107587