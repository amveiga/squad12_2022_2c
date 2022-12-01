Feature: Manejo TareaParteDeHoras
  Scenario: modificacion de horas trabajadas en una tarea modificable
    Given una tarea con 10 horas y estado "BORRADOR" el "01/12/2022" de proyecto "proyecto"
    When modifico las horas trabajadas por 200
    Then la tarea queda con 200 horas trabajadas

  Scenario: modificacion de horas trabajadas en una tarea no modificable
    Given una tarea con 10 horas y estado "APROBADO" el "01/12/2022" de proyecto "proyecto"
    When modifico las horas trabajadas por 200
    Then la tarea no se modifica

  Scenario: cargar una tarea con horas iguales a cero falla
    Given una tarea con 0 horas y estado "BORRADOR" el "01/12/2022" de proyecto "proyecto"
    When la cargo
    Then no es posible cargarla

#  Scenario: cargar una tarea con horas negativas
#    Given una tarea con -1 horas y estado "BORRADOR"
#    When la cargo
#    Then no es posible cargarla

  Scenario: cargar una tarea con estado inexistente falla
    Given una tarea con 100 horas y estado "INEXISTENTE" el "01/12/2022" de proyecto "proyecto"
    When la cargo
    Then no es posible cargarla

  Scenario: cargar una tarea exitosamente estado APROBADO
    Given una tarea con 100 horas y estado "APROBADO" el "01/12/2022" de proyecto "proyecto"
    When la cargo
    Then se carga con 100 horas y estado "APROBADO"

  Scenario: cargar una tarea exitosamente estado BORRADOR
    Given una tarea con 100 horas y estado "BORRADOR" el "01/12/2022" de proyecto "proyecto"
    When la cargo
    Then se carga con 100 horas y estado "BORRADOR"

  Scenario: cargar una tarea exitosamente estado DESAPROBADO
    Given una tarea con 100 horas y estado "DESAPROBADO" el "01/12/2022" de proyecto "proyecto"
    When la cargo
    Then se carga con 100 horas y estado "DESAPROBADO"

  Scenario: cargar una tarea exitosamente estado VALIDACION_PENDIENTE
    Given una tarea con 100 horas y estado "VALIDACION_PENDIENTE" el "01/12/2022" de proyecto "proyecto"
    When la cargo
    Then se carga con 100 horas y estado "VALIDACION_PENDIENTE"

  Scenario: cargar una tarea post una semana falla
    Given una tarea con 100 horas y estado "BORRADOR" el "20/11/2022" de proyecto "proyecto"
    When la cargo
    Then no es posible cargarla

  Scenario: obtención de tareas entre dos fechas exitoso
    Given una tarea con 100 horas y estado "BORRADOR" el "28/11/2022" de proyecto "proyecto"
    And una tarea con 10 horas y estado "APROBADO" el "29/11/2022" de proyecto "proyecto"
    And una tarea con 40 horas y estado "DESAPROBADO" el "30/11/2022" de proyecto "proyecto"
    When busco tareas entre el "27/11/2022" y el "31/11/2022" en estado "BORRADOR"
    Then retorna un listado de tareas entre las fechas correspondientes
