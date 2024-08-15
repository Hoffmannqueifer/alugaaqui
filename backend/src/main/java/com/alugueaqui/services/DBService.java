package com.alugueaqui.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alugueaqui.entities.Administrador;
import com.alugueaqui.entities.Funcionario;
import com.alugueaqui.entities.Postagem;
import com.alugueaqui.enums.Perfil;
import com.alugueaqui.repositories.AdministradorRepository;
import com.alugueaqui.repositories.FuncionarioRepository;
import com.alugueaqui.repositories.PostagemRepository;

import jakarta.transaction.Transactional;



@Service
public class DBService {
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void instanciaDB() {
		Administrador admin = new Administrador(null, "hoffmann", "75554426042", "hoffmann@gmail.com", encoder.encode("123456789") );
		admin.addPerfil(Perfil.ADMIN);
		Funcionario func = new Funcionario(null, "teste", "98814796068", "teste@gmail.com", encoder.encode("123"));
		func.addPerfil(Perfil.FUNCIONARIO);
		
		Postagem Po = new Postagem(null, "casa", "casa top","catole",new Date(),"");
		
		administradorRepository.saveAll(Arrays.asList(admin));
		funcionarioRepository.saveAll(Arrays.asList(func));
		postagemRepository.saveAll(Arrays.asList(Po));
	}
}
