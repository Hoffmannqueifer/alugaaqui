package com.alugueaqui.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alugueaqui.entities.Postagem;
import com.alugueaqui.repositories.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	public List<Postagem> findAll(){
		return postagemRepository.findAll();
	}
}
