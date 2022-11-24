package com.recursos;

import com.recursos.model.ParteDeHoras;
import com.recursos.model.Recurso;

import com.recursos.service.ParteDeHorasService;
import com.recursos.service.RecursoService;
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
	@ResponseStatus(HttpStatus.CREATED)
	public Recurso crearRecurso(@RequestBody Recurso recurso) {
		return recursoService.crearRecurso(recurso);
	}

	@GetMapping("/recursos")
	public Collection<Recurso> getRecursos() {
		return recursoService.getRecursos();
	}

	@GetMapping("/recursos/{legajo}")
	public ResponseEntity<Recurso> getRecursoById(@PathVariable Long legajo) {
		Optional<Recurso> recursoOptional = recursoService.findById(legajo);
		return ResponseEntity.of(recursoOptional);
	}

	// TODO: verificar que esta es la manera correcta de mandar los parámetros
	@GetMapping("/recursos/{nombre}/{apellido}")
	public ResponseEntity<Collection<Recurso>> getRecursoByName(@PathVariable String nombre, @PathVariable String apellido) {
		Optional<Collection<Recurso>> recursosOptional = recursoService.findByName(nombre, apellido);
		return ResponseEntity.of(recursosOptional);
	}

	// PARTE DE HORAS

	@PostMapping("recursos/{legajo}/parte_de_horas")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ParteDeHoras> createParte(@RequestBody ParteDeHoras parteDeHoras, @PathVariable Long legajo) {
		Optional<Recurso> optionalRecurso = recursoService.findById(legajo);

		if(optionalRecurso.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		parteDeHorasService.createParteDeHoras(parteDeHoras);

		return ResponseEntity.ok().build();
	}

	@GetMapping("recursos/parte_de_horas")
	public Collection<ParteDeHoras> getPartesDeHoras() { return parteDeHorasService.getParteDeHoras(); }

	@GetMapping("/recursos/{legajo}/parte_de_horas")
	public ResponseEntity<Collection<ParteDeHoras>> getParteByLegajo(@PathVariable Long legajo) {
		Optional<Recurso> optionalRecurso = recursoService.findById(legajo);

		if(optionalRecurso.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Optional<Collection<ParteDeHoras>> optionalParteDeHoras =  parteDeHorasService.getPartesByLegajo(legajo);
		return ResponseEntity.of(optionalParteDeHoras);
	}


	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
