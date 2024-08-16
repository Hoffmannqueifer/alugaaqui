package com.alugueaqui.entities;

import java.util.Base64;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Postagem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tipoPostagem;
	private String descricao;
	private String localidade;
	private Date dataPostagem;
	private String base64Image;
	
	public Postagem() {
		
	}
	
	public Postagem(Integer id, String tipoPostagem, String descricao, String localidade, Date dataPostagem, String base64Image) {
        this.id = id;
        this.tipoPostagem = tipoPostagem;
        this.descricao = descricao;
        this.localidade = localidade;
        this.dataPostagem = dataPostagem;
        this.base64Image = base64Image;
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipoPostagem() {
		return tipoPostagem;
	}
	public void setTipoPostagem(String tipoPostagem) {
		this.tipoPostagem = tipoPostagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public Date getDataPostagem() {
		return dataPostagem;
	}
	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(byte[] imageBytes) {
		this.base64Image = Base64.getEncoder().encodeToString(imageBytes);
	}

}
