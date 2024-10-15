package init.service;

import java.util.List;
import java.util.Optional;

import init.model.Alumno;

public interface AlumnosService {
	//no permite dar de alta un alumno con misma combinación nombre/curso
	boolean altaAlumno(Alumno alumno);
	List<Alumno> alumnos();
	List<String> cursos();
	List<Alumno> alumnosCurso(String curso);
	Optional<Alumno> alumnoPorId(int idAlumno);
	//devuelve el alumno eliminado. Si no hibiera ningún alumno
	//con ese email, devuelve null
	Alumno eliminarAlumno(String email);
	double notamedia();

}
