package com.example.esporte;

import com.example.esporte.controller.ProdutoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class EsporteApplication {

	public static void main(String[] args) {
		new File(ProdutoController.UPLOADED_FOLDER).mkdir();
		SpringApplication.run(EsporteApplication.class, args);
	}

}
