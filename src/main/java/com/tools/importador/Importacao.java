package com.tools.importador;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.tools.importador.service.ClienteService;
import com.tools.importador.service.VendaService;
import com.tools.importador.service.VendedorService;

@Service
public class Importacao {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private VendedorService vendedorService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private VendaService vendaService;
	

	public void iniciarImportacao() {
		 try {
		        Resource[] resources = applicationContext.getResources("file:C:/dados/in/*.dat");
		        if(resources.length > 0) {
		        	for(Resource resource : resources) {
		        		System.out.println("Iniciando leitura de: " + resource.getFilename());
		        		lerArquivoDat(resource);
		        		System.out.println("Finalizada leitura de: " + resource.getFilename());
		        		System.out.println("Iniciando processamento de: " + resource.getFilename());
		        		processarArquivoDat(resource);
		        		System.out.println("Finalizado processamento de: " + resource.getFilename());
		        	}
		        }else {
		        	System.out.println("Não foi encontrado nenhum arquivo para leitura");
		        }
		    } catch (IOException ex) {
		        ex.printStackTrace();
	    }
	}

	private void processarArquivoDat(Resource resource) {
		Path newFilePath = Paths.get("C:/dados/out/"+resource.getFilename()+".proc");
	    try {
	    	//criar o arquivo de processamento
			Files.createFile(newFilePath);
			
			//monta a mensagem com as informações
			String infos = getInfoForDatProcByArquivo(resource.getFilename());
		    byte[] strToBytes = infos.getBytes();

		    //escreve no arquivo
		    Files.write(newFilePath, strToBytes);
	    }catch (FileAlreadyExistsException alreadyExist) {
			System.out.println("Arquivo já processado: " + resource.getFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getInfoForDatProcByArquivo(String arquivo) {
		String mensagem = "";
		
		mensagem += "Quantidade de Clientes: " + clienteService.findByArquivo(arquivo).size();
		mensagem += "\nQuantidade de Vendedores: " + vendedorService.findByArquivo(arquivo).size();
		mensagem += "\nID da Venda de valor mais alto: " + vendaService.findTop1IdVendaOrderByValorTotalDesc(arquivo);
		mensagem += "\nNome do Vendedor que menos vendeu: " + vendaService.countByNomeVendedorOrderByCountAsc(arquivo);
		
		return mensagem;
	}

	private void lerArquivoDat(Resource resource) {
		try {
			Path path = Paths.get(resource.getFile().getAbsolutePath());
			List<String> read = Files.readAllLines(path);
			for(String s : read) {
				String[] split = s.split(";");
				if(split[0].equals("001")) {
					vendedorService.salvar(split, resource.getFilename());
				}else if(split[0].equals("002")) {
					clienteService.salvar(split, resource.getFilename());
				}else if(split[0].equals("003")) {
					vendaService.salvar(split, resource.getFilename());
				}else {
					System.out.println("Estrutura não identificada");
				}
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo: " + resource.getFilename());
			e.printStackTrace();
		}
	}

}
