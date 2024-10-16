package init.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.AlumnoDAO;
import init.model.Alumno;
import init.service.AlumnosService;

@Service
public class AlumnosServiceImpl implements AlumnosService {
	
	@Autowired
	AlumnoDAO alumnoDao;

	@Override
	public boolean altaAlumno(Alumno alumno) {
		
		/*
		 *  if(alumnosDao.findByNombreAndCurso(alumno.getNombre(), alumno.getCurso())==null) {
				alumnosDao.save(alumno);
				return true;
			}
			return false;
		 **/
		if (alumnoDao.findByNombreAndCurso(alumno.getNombre(), alumno.getCurso())!=null) {
			System.err.println("El nombre del alumno y curso ya existe. ");
			return false; 
		}
		else return (alumnoDao.save(alumno)!=null);
	}

	@Override
	public List<Alumno> alumnos() {
		// TODO Auto-generated method stub
		return alumnoDao.findAll();
	}

	@Override
	public List<String> cursos() {
		// ¿retornar solo los cursos sin filtrar?-> lo hacemos con @Query en el dao.
		return alumnoDao.findAllCursos();
	}
	

	@Override
	public List<Alumno> alumnosCurso(String curso) {
		return alumnoDao.findByCurso(curso); 
	}

	@Override
	public Optional<Alumno> alumnoPorId(int idAlumno) {
		return alumnoDao.findById(idAlumno);
	}

	@Override
	public Alumno eliminarAlumno(String email) {
		Optional<Alumno> alumno = alumnoDao.findByEmail(email);
		if (alumno.isPresent()) {
			alumnoDao.deleteByEmail(email);
			return alumno.get();
			
		}
		return null;
	}

	@Override
	public double notamedia() {
		return alumnoDao.average();
	}}
