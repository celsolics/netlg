package com.csli.netlg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.csli.netlg.domain.Categoria;

@SpringBootApplication
public class NetlgApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(NetlgApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		repo.saveOll(Arrays.asList(c1, c2));
	}
}
