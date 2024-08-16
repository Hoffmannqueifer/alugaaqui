package com.alugueaqui.servicies;

import com.alugueaqui.entities.Postagem;
import com.alugueaqui.repositories.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	public List<Postagem> findAll(){
		return postagemRepository.findAll();
	}
}
