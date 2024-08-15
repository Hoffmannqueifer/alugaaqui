package com.alugueaqui.entities;

import com.alugueaqui.enums.Perfil;

import jakarta.persistence.Entity;

@Entity
public class Funcionario extends Usuario {

	public Funcionario() {
		super();
		addPerfil(Perfil.FUNCIONARIO);
	}

	public Funcionario(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.FUNCIONARIO);
	}
}
