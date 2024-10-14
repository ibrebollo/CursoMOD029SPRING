package init.service;

import java.util.List;

import init.model.Item;

public interface BuscadorService {

		List<Item> buscar(String tematica);
}
