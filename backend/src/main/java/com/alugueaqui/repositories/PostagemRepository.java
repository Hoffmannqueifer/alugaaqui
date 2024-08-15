package com.alugueaqui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alugueaqui.entities.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Integer> {

}
