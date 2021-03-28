package com.tools.importador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tools.importador.model.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Long>{

	Long findTop1IdVendaByArquivoOrderByValorTotalDesc(String arquivo);

	Venda findTop1ByArquivoOrderByValorTotalDesc(String arquivo);

    @Query("SELECT v.nomeVendedor, count(v) as quantidadeDeVendas from Venda v where v.arquivo = :arquivo group by v.nomeVendedor order by quantidadeDeVendas ASC")
	List<Object[]> countByNomeVendedorOrderByCountAsc(String arquivo);

}
