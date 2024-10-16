package init.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import init.model.Alumno;
import jakarta.transaction.Transactional;

public interface AlumnoDAO extends JpaRepository<Alumno, Integer> {

	Object findByNombreAndCurso(String nombre, String curso);
	
	List<Alumno> findByCurso(String curso);
	
	@Query("select distinct (a.curso) from Alumno a")
	List<String> findAllCursos();

	Optional<Alumno> findByEmail(String email);
	
	@Transactional
	@Modifying
	void deleteByEmail(String email);

	@Query("select avg(a.nota) from Alumno a")
	double average();

}
