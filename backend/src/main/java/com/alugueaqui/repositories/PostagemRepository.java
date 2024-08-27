package com.alugueaqui.repositories;

import com.alugueaqui.entities.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
}
