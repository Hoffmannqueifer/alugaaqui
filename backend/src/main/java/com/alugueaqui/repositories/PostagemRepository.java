package com.alugueaqui.repositories;

import com.alugueaqui.entities.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    @Query("select p from Postagem p ")
    Page<Postagem> findAllPageable(Pageable pageable);
}
