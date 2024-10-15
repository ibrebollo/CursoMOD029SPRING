package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Estudiante;
import init.service.FormacionService;

@RestController
public class FormacionController {
	@Autowired
	FormacionService formacionService;
	
	@GetMapping(value="estudiantes/{nota}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Estudiante>> estudiantesAplicados(@PathVariable("nota") double notaMin){
		return new ResponseEntity<>(formacionService.estudiantesNotaMinima(notaMin),HttpStatus.OK);
	}
	
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaEstudiante (@RequestBody Estudiante estudiante){
		formacionService.nuevoEstudiante(estudiante);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
