package init.service;

import java.util.List;

import init.model.Estudiante;

public interface FormacionService {
	List<Estudiante> estudiantesNotaMinima(double nota);
	void nuevoEstudiante(Estudiante estudiante);

}
