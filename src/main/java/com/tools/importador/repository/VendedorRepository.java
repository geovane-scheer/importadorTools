package com.tools.importador.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tools.importador.model.Vendedor;

@Repository
public interface VendedorRepository extends CrudRepository<Vendedor, Long>{

	List<Vendedor> findByArquivo(String arquivo);

}
