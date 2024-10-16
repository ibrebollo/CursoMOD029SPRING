package init.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.model.Estudiante;
import init.model.TokenResponse;
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
	
	@Value("${keycloak.url}")
	String urlKeycloak; // Dirección de keyCloak TODO IBR PENDIENTE no he conseguido montarlo.
	@Value("${keycloak.clientId}")
	String clientId;
	@Value("${keycloak.username}")
	String username;
	@Value("${keycloak.pass}")
	String pass;
	@Value("${keycloak.grantType}")
	String grantType; 
	
	String token; 
	
	public void init() {
		token = getToken();
		
	}
	
	@Override
	public List<Estudiante> estudiantesNotaMinima(double nota) {
		return Arrays.stream(
				restClient
				.get()
				.uri(urlBase+"alumnos")
				//.header("Authorization", "Basic "+getBase64(usuario, password)) // hay q ponerlo siempre antes del retrieve
				.header("Authorization", "Bearer "+ token) // EJERCICIO 10
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
	
	private String getToken (){
		MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", pass);
		params.add("grant_type", grantType);
		
		return restClient
		.post()
		.uri(urlKeycloak)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.body(params)
		.retrieve()
		.body(TokenResponse.class)
		.getAccess_token();
	}
 

}
