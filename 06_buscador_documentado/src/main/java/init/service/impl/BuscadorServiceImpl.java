package init.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.BuscadorDAO;
import init.model.Item;
import init.service.BuscadorService;

@Service
public class BuscadorServiceImpl implements BuscadorService{
	
	@Autowired
	BuscadorDAO buscadorDao;

	@Override
	public List<Item> buscar(String tematica) {
		return buscadorDao.findByTematica(tematica);		
	}

	@Override
	public boolean altaItem(Item item) {
		//1º Buscamos si existe otro igual por url
		//2º Si ya existe devolvemos false. 		
		//3º Y sino, lo damos de alta con save y devolvemos true. 
		if (item!=null) {
			if (buscadorDao.findByUrl(item.getUrl())!=null) {
				System.err.println("El item ya existe por tener la misma url: " + item.getUrl());
				return false; 
			}
			else {
				buscadorDao.save(item);				 
			} 
		}
		return true;
		
		
		
	}
}
