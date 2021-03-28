package com.tools.importador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ImportadorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ImportadorApplication.class, args);

		System.out.println("Importação iniciada...");
        context.getBean(Importacao.class).iniciarImportacao();
        System.out.println("Importação finalizada...");
	}

}
