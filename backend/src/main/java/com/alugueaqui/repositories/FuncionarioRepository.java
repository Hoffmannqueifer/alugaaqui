package com.alugueaqui.repositories;

import com.alugueaqui.entities.Funcionario;
import com.alugueaqui.repositories.projections.FuncionarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select c from Funcionario c")
    Page<FuncionarioProjection> findAllPageable(Pageable pageable);

    Funcionario findByUsuarioId(Long id);

    Optional<Funcionario> findByCpf(String cpf);
}
