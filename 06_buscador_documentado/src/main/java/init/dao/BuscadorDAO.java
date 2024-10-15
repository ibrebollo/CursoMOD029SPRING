package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Item;

public interface BuscadorDAO extends JpaRepository<Item, Integer>{
	List<Item> findByTematica(String tematica);
	
	Item findByUrl(String url);

}
