Feature: Manejo TareaParteDeHoras

  Scenario: modificacion de horas trabajadas en una tarea exitoso
    Given una tarea con 100 horas trabajadas
    When modifico las horas trabajadas por 200
    Then la tarea queda con 200 horas trabajadas

