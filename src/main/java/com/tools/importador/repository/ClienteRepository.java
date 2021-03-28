package com.tools.importador.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tools.importador.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	List<Cliente> findByArquivo(String arquivo);

}
