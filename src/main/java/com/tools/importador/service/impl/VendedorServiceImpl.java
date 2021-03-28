package com.tools.importador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tools.importador.model.Vendedor;
import com.tools.importador.repository.VendedorRepository;
import com.tools.importador.service.VendedorService;


@Service
public class VendedorServiceImpl implements VendedorService{
	
	@Autowired
	private VendedorRepository vendedorRepository;

	@Override
	public void salvar(String[] split, String arquivo) {
		Vendedor vendedor = new Vendedor();
		vendedor.setCpf(split[1]);
		vendedor.setNome(split[2]);
		vendedor.setSalario(Double.parseDouble(split[3]));
		vendedor.setArquivo(arquivo);
		vendedorRepository.save(vendedor);
	}

	@Override
	public List<Vendedor> findAll() {
		return (List<Vendedor>) vendedorRepository.findAll();
	}

	@Override
	public List<Vendedor> findByArquivo(String arquivo) {
		return vendedorRepository.findByArquivo(arquivo);
	}

}
