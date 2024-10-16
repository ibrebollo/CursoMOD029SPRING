package init.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Estudiante {
	private String nombre;
	private String curso;
	private String email;	
	@JsonProperty("nota")
	private double calificacion; // OjO pq el nombre es diferente a la nota de la entidad del otro servicio. 
	
	
	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Estudiante(String nombre, String curso, String email, double calificacion) {
		super();
		this.nombre = nombre;
		this.curso = curso;
		this.email = email;
		this.calificacion = calificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	} 

}
