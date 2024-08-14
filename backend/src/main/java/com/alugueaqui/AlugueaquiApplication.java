package com.alugueaqui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alugueaqui.services.DBService;

@SpringBootApplication
public class AlugueaquiApplication implements ApplicationRunner {
	
	@Autowired
	private DBService dbService;

	public static void main(String[] args) {
		SpringApplication.run(AlugueaquiApplication.class, args);
	}
	
	@Override
    public void run(ApplicationArguments args) throws Exception {
        dbService.instanciaDB();
    }

}
