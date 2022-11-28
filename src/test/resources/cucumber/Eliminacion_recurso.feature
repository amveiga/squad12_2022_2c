Feature: Eliminacion de recurso

  Scenario: eliminacion de recurso por legajo exitosa
    Given un recurso de nombre "Pedro", apellido "Gallino" y legajo 107587
    And un recurso de nombre "Raul", apellido "Gomez" y legajo 107588
    When elimino por legajo 107587
    Then se elimina el recurso 107587
    And no se elimina el recurso 107588