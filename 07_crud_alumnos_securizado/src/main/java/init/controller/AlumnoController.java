package init.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Alumno;
import init.service.AlumnosService;

@RestController
public class AlumnoController {
	@Autowired
	AlumnosService alumnoService;
	
	@PostMapping(value = "altaAlumno", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> nuevoItem(@RequestBody Alumno alumno) {
		if (alumnoService.altaAlumno(alumno)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}	
	

	@GetMapping(value = "alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Alumno>> buscarAlumnos() {
		return new ResponseEntity<>(alumnoService.alumnos(), HttpStatus.OK);
	}	
	
	@GetMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> buscarCursos() {
		return new ResponseEntity<>(alumnoService.cursos(), HttpStatus.OK);
	}		
	
	@GetMapping(value = "alumnosCurso/{curso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Alumno>> alumnosCurso(@PathVariable("curso") String curso) {
		return new ResponseEntity<>(alumnoService.alumnosCurso(curso), HttpStatus.OK);
	}		
	
	@GetMapping(value = "alumnoPorId/{idAlumno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Alumno> alumnoPorId(@PathVariable("idAlumno") int idAlumno) {
		Optional<Alumno> alumnoPorId = alumnoService.alumnoPorId(idAlumno);
		if (alumnoPorId.isPresent()) {			
			return new ResponseEntity<>(alumnoPorId.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	@DeleteMapping(value="eliminar",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alumno> eliminar(@RequestParam("email") String email){
		Alumno alumno=alumnoService.eliminarAlumno(email);
		if(alumno!=null) {
			return new ResponseEntity<>(alumno,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value="media",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> media(){
		return new ResponseEntity<>(String.valueOf(alumnoService.notamedia()),HttpStatus.OK);
	}	

}
