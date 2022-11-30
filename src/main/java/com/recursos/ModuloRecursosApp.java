package com.recursos;

import com.recursos.exceptions.LegajoNoEncontradoException;
import com.recursos.exceptions.NoSePuedeModificarUnParteAprobadoException;
import com.recursos.exceptions.ParteDeHorasNoEncontradoException;
import com.recursos.exceptions.TareaNoEncontradaException;
import com.recursos.model.ParteDeHoras;
import com.recursos.model.Recurso;

import com.recursos.model.TareaDelParteDeHora;
import com.recursos.service.ParteDeHorasService;
import com.recursos.service.RecursoService;
import com.recursos.service.TareaDelParteDeHorasService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class ModuloRecursosApp {


	@Autowired
	private RecursoService recursoService;

	@Autowired
	private ParteDeHorasService parteDeHorasService;

	@Autowired
	private TareaDelParteDeHorasService tareasDelParteDeHorasService;

	public static void main(String[] args) {
		SpringApplication.run(ModuloRecursosApp.class, args);
	}


	// ******   RECURSOS  ******
	@PostMapping("/recursos")
	@ApiOperation(value = "Crear un recurso nuevo")
	@ResponseStatus(HttpStatus.CREATED)
	public Recurso crearRecurso(@RequestBody Recurso recurso) {
		return recursoService.crearRecurso(recurso);
	}

	@GetMapping("/recursos")
	@ApiOperation(value = "Obtener TODOS los recursos")
	public Collection<Recurso> getRecursos() {
		String psaRecursosURL = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Recurso[]> response = restTemplate.getForEntity(psaRecursosURL, Recurso[].class);
		return recursoService.getRecursos(response);
	}

	@GetMapping("/recursos/{legajo}")
	@ApiOperation(value = "Obtener un recurso por legajo")
	public ResponseEntity<Recurso> getRecursoById(@PathVariable Long legajo) {
		Optional<Recurso> recursoOptional = recursoService.findById(legajo);
		return ResponseEntity.of(recursoOptional);
	}

	@GetMapping("/recursos/full_name")
	@ApiOperation(value = "Obtener un recurso por nombre y apellido")
	public ResponseEntity<Collection<Recurso>> getRecursoByName(@RequestParam String nombre, @RequestParam String apellido) {
		Optional<Collection<Recurso>> recursosOptional = recursoService.findByNameAndFamilyName(nombre, apellido);
		return ResponseEntity.of(recursosOptional);
	}

	@GetMapping("/recursos/name")
	@ApiOperation(value = "Obtener un recurso por nombre")
	public ResponseEntity<Collection<Recurso>> getRecursoByFirstName(@RequestParam String nombre) {
		Optional<Collection<Recurso>> recursosOptional = recursoService.findByFirstName(nombre);
		return ResponseEntity.of(recursosOptional);
	}

	@GetMapping("/recursos/family_name")
	@ApiOperation(value = "Obtener un recurso por apellido")
	public ResponseEntity<Collection<Recurso>> getRecursoByFamilyName(@RequestParam String apellido) {
		Optional<Collection<Recurso>> recursosOptional = recursoService.findByFamilyName(apellido);
		return ResponseEntity.of(recursosOptional);
	}

	@DeleteMapping("/recursos/{legajo}")
	@ApiOperation(value = "Eliminar un recurso por legajo")
	public void deleteRecurso(@PathVariable Long legajo) {
		recursoService.deleteById(legajo);
	}


	// ******   PARTE DE HORAS  ******

	@PostMapping("/recursos/{legajo}/parte_de_horas")
	@ApiOperation(value = "Crear un parte de horas nuevo para un legajo",
			notes = "No se puede cargar un parte de horas anterior a la semana actual\n" +
					"No se puede cargar una cantidad de horas menor o igual a 0\n" +
					"Los estados posibles son: BORRADOR, VALIDACION_PENDIENTE, APROBADO, DESAPROBADO\n" +
					"Los tipos de tareas posibles son: TAREA_PROYECTO, INCIDENCIA, ADMINISTRATIVA_REUNION, ADMINISTRATIVA_CAPACITACION, ADMINISTRATIVA_CURSO, GUARDIA, LICENCIA")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ParteDeHoras> createParte(@RequestBody TareaDelParteDeHora[] tareasDelParteDeHoras, @PathVariable Long legajo) {
		if(!recursoService.existsById(legajo) ||
			!tareasDelParteDeHorasService.validateTasks(tareasDelParteDeHoras)) {
			throw new LegajoNoEncontradoException("Error en la carga de datos");
		}

		ParteDeHoras parteDeHoras = parteDeHorasService.createParteDeHoras(legajo);

		tareasDelParteDeHorasService.saveAll(tareasDelParteDeHoras, parteDeHoras.getParteDeHorasID());

		return ResponseEntity.ok().build();
	}

	@GetMapping("/recursos/parte_de_horas")
	@ApiOperation(value = "Obtener TODOS los partes de horas")
	public Collection<ParteDeHoras> getPartesDeHoras() {
		return parteDeHorasService.getParteDeHoras();
	}

	@GetMapping("/recursos/{legajo}/parte_de_horas")
	@ApiOperation(value = "Obtener los partes de horas de un legajo")
	public ResponseEntity<Collection<ParteDeHoras>> getParteByLegajo(@PathVariable Long legajo) {
		Optional<Recurso> optionalRecurso = recursoService.findById(legajo);
		Optional<Collection<ParteDeHoras>> optionalParteDeHoras =  parteDeHorasService.getPartesByLegajo(legajo);
		return ResponseEntity.of(optionalParteDeHoras);
	}

	@DeleteMapping("/recursos/parte_de_horas/{parteDeHorasID}")
	@ApiOperation(value = "Eliminar un parte de horas")
	public void deleteParteDeHoras(@PathVariable Long parteDeHorasID) {
		parteDeHorasService.deleteById(parteDeHorasID);
	}



	// ******   TAREAS  ******

	@GetMapping("/recursos/parte_de_horas/{parteDeHorasId}")
	@ApiOperation(value = "Obtener TODAS las tareas de un parte de horas")
	public Collection<TareaDelParteDeHora> getTareasByParteDeHoras(@PathVariable Long parteDeHorasId) {
		return tareasDelParteDeHorasService.getTareasByParteDeHoraId(parteDeHorasId);
	}


	@GetMapping("/recursos/{legajo}/tareas")
	@ApiOperation(value = "Obtener todas las tareas de un legajo")
	public Collection<TareaDelParteDeHora> getTareasByLegajo(@PathVariable Long legajo) {
		Collection<TareaDelParteDeHora> tareasTotales = new ArrayList<>();
		if (!recursoService.existsById(legajo)){
			throw new LegajoNoEncontradoException("Legajo no encontrado");
		}
		Optional<Collection<ParteDeHoras>> partesDeHoras = parteDeHorasService.getPartesByLegajo(legajo);
		if (partesDeHoras.isEmpty()) {
			return tareasTotales;
		} else {
			for (ParteDeHoras parteDeHoras: partesDeHoras.get()){
				tareasTotales.addAll(tareasDelParteDeHorasService.getTareasByParteDeHoraId(parteDeHoras.getParteDeHorasID()));
			}
		}
		return tareasTotales;
	}


	@PutMapping("/recursos/{tareaDelParteDeHoraId}/horas_trabajadas")
	@ApiOperation(value = "Modificar la cantidad de horas trabajadas de una tarea",
			notes = "No se puede modificar un parte de horas que ya fue aprobado\n" +
					"No se puede cargar una cantidad de horas menor o igual a 0")
	public ResponseEntity<ParteDeHoras> updateCantidadDeHorasTrabajadasDeUnaTarea(@PathVariable Long tareaDelParteDeHoraId, @RequestBody int cantidadDeHorasNuevas) {
		tareasDelParteDeHorasService.modificarCantHoras(tareaDelParteDeHoraId, cantidadDeHorasNuevas);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/recursos/{tareaDelParteDeHoraId}/nuevo_estado")
	@ApiOperation(value = "Modificar el estado de una tarea",
			notes = "No se puede modificar un parte de horas que ya fue aprobado\n" +
					"Los estados posibles son: BORRADOR, VALIDACION_PENDIENTE, APROBADO, DESAPROBADO\n")
	public ResponseEntity<ParteDeHoras> updateEstadoDeUnaTarea(@PathVariable Long tareaDelParteDeHoraId, @RequestBody String estado) {
		tareasDelParteDeHorasService.modificarEstado(tareaDelParteDeHoraId, estado);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/recursos/tareaDelParteDeHoraId/{tareaDelParteDeHoraId}")
	@ApiOperation(value = "Eliminar una tarea")
	public void deleteTarea(@PathVariable Long tareaDelParteDeHoraId) {
		tareasDelParteDeHorasService.deleteById(tareaDelParteDeHoraId);
	}


	//   *******     REPORTES   ********

	@GetMapping("/reportes/tareas/estados")
	@ApiOperation(value = "obtener todas las tareas de un cierto estado")
	public Collection<TareaDelParteDeHora> getTareasPorEstado(@RequestParam String estado) {
		return tareasDelParteDeHorasService.obtenerTareasPorEstado(estado);
	}

	@GetMapping("/reportes/tareas/proyecto")
	@ApiOperation(value = "obtener todas las tareas aprobadas de un cierto proyecto")
	public Collection<TareaDelParteDeHora> getTareasPorProyecto(@RequestParam String proyectoId) {
		return tareasDelParteDeHorasService.obtenerTareasPorProyectoId(proyectoId, "APROBADO");
	}


	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
				.apis( RequestHandlerSelectors.basePackage( "com.recursos" ) )
			.paths(PathSelectors.any())
			.build();
	}
}
