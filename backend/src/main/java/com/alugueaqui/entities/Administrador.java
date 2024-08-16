package com.alugueaqui.entities;

import com.alugueaqui.enums.Perfil;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario {

	public Administrador() {
		super();
		addPerfil(Perfil.ADMIN);
	}

	public Administrador(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.ADMIN);
	}
}
