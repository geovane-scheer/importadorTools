package com.tools.importador.service;

import java.util.List;

import com.tools.importador.model.Venda;

public interface VendaService {

	void salvar(String[] split, String arquivo);

	List<Venda> findAll();

	Long findTop1IdVendaOrderByValorTotalDesc(String arquivo);

	String countByNomeVendedorOrderByCountAsc(String arquivo);

}
