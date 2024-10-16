package init.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Estudiante;
import init.service.FormacionService;

@Service
public class FormacionServiceImpl implements FormacionService {
	
	@Autowired
	RestClient restClient;
	
	@Value("${remote.user}")
	String usuario;
	
	@Value("${remote.pass}")
	String password;

	String urlBase="http://localhost:7500/crudalumnos/";
	@Override
	public List<Estudiante> estudiantesNotaMinima(double nota) {
		return Arrays.stream(
				restClient
				.get()
				.uri(urlBase+"alumnos")
				.header("Authorization", "Basic "+getBase64(usuario, password)) // hay q ponerlo siempre antes del retrieve
				.retrieve()
				.body(Estudiante[].class))
				.filter(e->e.getCalificacion()>=nota) // ojo pq los nombres son diferentes califacacion != notas (se puede mapear mediante la anotación @JsonProperty
				.toList();
	}
	@Override
	public void nuevoEstudiante(Estudiante estudiante) {
		restClient
		.post()
		.uri(urlBase+"altaAlumno")
		.contentType(MediaType.APPLICATION_JSON)
		.body(estudiante)
		.header("Authorization", "Basic "+getBase64(usuario, password)) // hay q ponerlo siempre antes del retrieve
		.retrieve()
		.toBodilessEntity();
		
	}
	
	private String getBase64(String us, String pwd) {

		String cad=us+":"+pwd;
		return Base64.getEncoder().encodeToString(cad.getBytes());

	}
 

}
