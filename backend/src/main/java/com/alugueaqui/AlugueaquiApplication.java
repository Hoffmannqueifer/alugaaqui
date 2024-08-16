package com.alugueaqui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlugueaquiApplication {
	
//	@Autowired
//	private DBService dbService;

	public static void main(String[] args) {
		SpringApplication.run(AlugueaquiApplication.class, args);
	}
	
//	@Override
//    public void run(ApplicationArguments args) throws Exception {
//        dbService.instanciaDB();
//    }

}
