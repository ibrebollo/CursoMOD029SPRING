package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import init.model.Item;
import init.service.BuscadorService;

@RestController
public class BuscadorController {
	@Autowired //inyección de dependencias
	BuscadorService buscadorService;
	
	@GetMapping(value="buscar/{tem}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Item> buscarItems(@PathVariable("tem") String tematica){
		return buscadorService.buscar(tematica);
	}
}