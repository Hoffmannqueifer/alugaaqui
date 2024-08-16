package com.alugueaqui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alugueaqui.entities.Postagem;
import com.alugueaqui.services.PostagemService;

@RestController
@RequestMapping(value = "/imoveis")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> findAll(){
		List<Postagem> list = postagemService.findAll();
		return ResponseEntity.ok().body(list);
	}
}
