package com.alugueaqui.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_IMOVEIS_TIPOS")
public class ImovelCategoria  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imovel_categoria")
    private Long id;

    private String descricao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
