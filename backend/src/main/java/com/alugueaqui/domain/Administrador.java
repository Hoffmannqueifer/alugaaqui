package com.alugueaqui.domain;

import com.alugueaqui.enums.Perfil;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends User {

	public Administrador() {
		super();
		addPerfil(Perfil.ADMIN);
	}

	public Administrador(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.ADMIN);
	}
}
