package com.tools.importador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tools.importador.model.Vendedor;
import com.tools.importador.service.VendedorService;

@RestController
@RequestMapping("/vendedores")
public class VendendorController {
	
	@Autowired
	private VendedorService vendedorService;
	
	@GetMapping(value = "/all")
	public List<Vendedor> getAll() {
		return vendedorService.findAll();
	}

}
