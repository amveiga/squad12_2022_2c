Feature: Manejo TareaParteDeHoras

  Scenario: modificacion de horas trabajadas en una tarea modificable
    Given una tarea con 100 horas trabajadas no aprobada
    When modifico las horas trabajadas por 200
    Then la tarea queda con 200 horas trabajadas

  Scenario: modificacion de horas trabajadas en una tarea no modificable
    Given una tarea con 100 horas trabajadas aprobada
    When modifico las horas trabajadas por 200
    Then la tarea queda con 100 horas trabajadas

  Scenario: cargar una tarea con horas iguales a cero
    Given una tarea con 0 horas trabajadas aprobada
    When la cargo
    Then no es posible cargarla

  Scenario: cargar una tarea con horas negativas
    Given una tarea con -1 horas trabajadas
    When la cargo
    Then no es posible cargarla

  Scenario: cargar una tarea con estado inexistente
    Given una tarea con estado "INEXISTENTE"
    When la cargo
    Then no es posible cargarla

  Scenario: cargar tarea con estado existente
    Given una tarea con estado "BORRADOR"
    When la cargo
    Then se carga correctamente
