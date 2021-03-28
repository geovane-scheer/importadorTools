package com.tools.importador.service;

import java.util.List;

import com.tools.importador.model.Vendedor;

public interface VendedorService {

	void salvar(String[] split, String arquivo);

	List<Vendedor> findAll();

	List<Vendedor> findByArquivo(String arquivo);

}
