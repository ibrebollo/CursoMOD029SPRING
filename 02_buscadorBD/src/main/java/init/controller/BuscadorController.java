package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Item;
import init.service.BuscadorService;

@RestController
public class BuscadorController {
	@Autowired //inyección de dependencias
	BuscadorService buscadorService;
	
	@GetMapping(value="buscar/{tem}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> buscarItems(@PathVariable("tem") String tematica){
		return new ResponseEntity<>(buscadorService.buscar(tematica),HttpStatus.OK);
	}
	
	@PostMapping(value="alta", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void nuevoItem(@RequestBody Item item) {
		buscadorService.altaItem(item);
	}
}