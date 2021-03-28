package com.tools.importador.service;

import java.util.List;

import com.tools.importador.model.Cliente;

public interface ClienteService {

	void salvar(String[] split, String arquivo);

	List<Cliente> findAll();

	List<Cliente> findByArquivo(String arquivo);

}
