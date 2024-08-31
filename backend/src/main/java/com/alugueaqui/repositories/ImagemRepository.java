package com.alugueaqui.repositories;

import com.alugueaqui.entities.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    List<Imagem> findByPostagemId(Long postagemId);
}
