package com.recursos;

import com.recursos.exceptions.CargaInvalidaException;
import com.recursos.exceptions.LegajoNoEncontradoException;
import com.recursos.model.ParteDeHoras;
import com.recursos.model.Recurso;

import com.recursos.service.ParteDeHorasService;
import com.recursos.service.RecursoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class ModuloRecursosApp {

	// TODO: aca declaramos los servicios
	@Autowired
	private RecursoService recursoService;

	@Autowired
	private ParteDeHorasService parteDeHorasService;

	public static void main(String[] args) {
		SpringApplication.run(ModuloRecursosApp.class, args);
	}

	// TODO: aca van los endpoints

	// RECURSOS
	@PostMapping("/recursos")
	@ApiOperation(value = "Crear un recurso nuevo")
	@ResponseStatus(HttpStatus.CREATED)
	public Recurso crearRecurso(@RequestBody Recurso recurso) {
		return recursoService.crearRecurso(recurso);
	}

	@GetMapping("/recursos")
	@ApiOperation(value = "Mostrar todos los recursos")
	public Collection<Recurso> getRecursos() {
		return recursoService.getRecursos();
	}

	@GetMapping("/recursos/{legajo}")
	@ApiOperation(value = "Obtener un recurso por legajo")
	public ResponseEntity<Recurso> getRecursoById(@PathVariable Long legajo) {
		Optional<Recurso> recursoOptional = recursoService.findById(legajo);
		return ResponseEntity.of(recursoOptional);
	}

	// TODO: verificar que esta es la manera correcta de mandar los parámetros
	@GetMapping("/recursos/{nombre}/{apellido}")
	@ApiOperation(value = "Obtener un recurso por nombre y apellido")
	public ResponseEntity<Collection<Recurso>> getRecursoByName(@PathVariable String nombre, @PathVariable String apellido) {
		Optional<Collection<Recurso>> recursosOptional = recursoService.findByNameAndFamilyName(nombre, apellido);
		return ResponseEntity.of(recursosOptional);
	}

	@GetMapping("/recursos/{nombre}")
	@ApiOperation(value = "Obtener un recurso por nombre")
	public ResponseEntity<Collection<Recurso>> getRecursoByFirstName(@PathVariable String nombre) {
		Optional<Collection<Recurso>> recursosOptional = recursoService.findByFirstName(nombre);
		return ResponseEntity.of(recursosOptional);
	}

	@GetMapping("/recursos/{apellido}")
	@ApiOperation(value = "Obtener un recurso por apellido")
	public ResponseEntity<Collection<Recurso>> getRecursoByFamilyName(@PathVariable String apellido) {
		Optional<Collection<Recurso>> recursosOptional = recursoService.findByFamilyName(apellido);
		return ResponseEntity.of(recursosOptional);
	}

	// PARTE DE HORAS

	@PostMapping("recursos/{legajo}/parte_de_horas")
	@ApiOperation(value = "Crear un parte de horas nuevo para un recurso por legajo", notes = "No se puede cargar un parte de horas anterior a la semana actual")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ParteDeHoras> createParte(@RequestBody ParteDeHoras parteDeHoras, @PathVariable Long legajo) {
		Optional<Recurso> optionalRecurso = recursoService.findById(legajo);
		if(optionalRecurso.isEmpty()) {
			throw new LegajoNoEncontradoException("No se encontró el legajo");
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Optional<ParteDeHoras> optionalParteDeHoras = parteDeHorasService.createParteDeHoras(parteDeHoras);
		if(optionalParteDeHoras.isEmpty()) {
			throw new CargaInvalidaException("No se pueden cargar horas de trabajo previas a 1 semana");
			//return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.of(optionalParteDeHoras);
	}

	@GetMapping("recursos/parte_de_horas")
	@ApiOperation(value = "Obtener todos los partes de horas")
	public Collection<ParteDeHoras> getPartesDeHoras() { return parteDeHorasService.getParteDeHoras(); }

	@GetMapping("/recursos/{legajo}/parte_de_horas")
	@ApiOperation(value = "Obtener los partes de horas de un recurso por legajo")
	public ResponseEntity<Collection<ParteDeHoras>> getParteByLegajo(@PathVariable Long legajo) {
		Optional<Recurso> optionalRecurso = recursoService.findById(legajo);

		if(optionalRecurso.isEmpty()) {
			throw new LegajoNoEncontradoException("No se encontró el legajo");
			//return ResponseEntity.notFound().build();
		}

		Optional<Collection<ParteDeHoras>> optionalParteDeHoras =  parteDeHorasService.getPartesByLegajo(legajo);
		return ResponseEntity.of(optionalParteDeHoras);
	}

	@DeleteMapping("/recusos/{legajo}")
	@ApiOperation(value = "Eliminar un recurso por legajo")
	public void deleteRecurso(@PathVariable Long legajo) {
		recursoService.deleteById(legajo);
	}

	@DeleteMapping("/recusos/parte_de_horas")
	@ApiOperation(value = "Eliminar un parte de horas")
	public void deleteParteDeHoras(@PathVariable Long parteDeHorasID) {
		parteDeHorasService.deleteById(parteDeHorasID);
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
