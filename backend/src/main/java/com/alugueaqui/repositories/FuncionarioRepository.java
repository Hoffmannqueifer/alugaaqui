package com.alugueaqui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alugueaqui.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
