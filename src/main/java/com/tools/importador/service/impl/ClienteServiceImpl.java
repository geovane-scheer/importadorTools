package com.tools.importador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tools.importador.model.Cliente;
import com.tools.importador.repository.ClienteRepository;
import com.tools.importador.service.ClienteService;


@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void salvar(String[] split, String arquivo) {
		Cliente cliente = new Cliente();
		cliente.setCnpj(split[1]);
		cliente.setNome(split[2]);
		cliente.setRamoDeAtividade(split[3]);
		cliente.setArquivo(arquivo);
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public List<Cliente> findByArquivo(String arquivo) {
		return clienteRepository.findByArquivo(arquivo);
	}

}
