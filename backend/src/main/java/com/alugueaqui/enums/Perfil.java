package com.alugueaqui.enums;

public enum Perfil {

	ADMIN(0, "ROLE_ADMIN"), FUNCIONARIO(1, "ROLE_FUNCIONARIO");
	
	private Integer codigo;
	private String descricao;
	
	private Perfil() {
		
	}
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil Inválido");
	}
}
