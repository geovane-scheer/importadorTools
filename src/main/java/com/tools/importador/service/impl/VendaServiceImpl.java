package com.tools.importador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tools.importador.model.Venda;
import com.tools.importador.repository.VendaRepository;
import com.tools.importador.service.VendaService;


@Service
public class VendaServiceImpl implements VendaService{
	
	@Autowired
	private VendaRepository vendaRepository;

	@Override
	public void salvar(String[] split, String arquivo) {
		Venda venda = new Venda();
		venda.setIdVenda(Long.parseLong(split[1]));
		venda.setIdItem(Long.parseLong(split[2]));
		venda.setQuantidadeItem(Long.parseLong(split[3]));
		venda.setPrecoItem(Double.parseDouble(split[4]));
		venda.setNomeVendedor(split[5]);
		venda.setArquivo(arquivo);
		vendaRepository.save(venda);
	}

	@Override
	public List<Venda> findAll() {
		return (List<Venda>) vendaRepository.findAll();
	}

	@Override
	public Long findTop1IdVendaOrderByValorTotalDesc(String arquivo) {
		return vendaRepository.findTop1ByArquivoOrderByValorTotalDesc(arquivo).getIdVenda();
	}

	@Override
	public String countByNomeVendedorOrderByCountAsc(String arquivo) {
		List<Object[]> list = vendaRepository.countByNomeVendedorOrderByCountAsc(arquivo);
		return (String) list.get(0)[0];
	}

}
