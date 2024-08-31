package com.alugueaqui.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_VEICULOS_CATEGORIAS")
public class VeiculoCategoria  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo_categoria")
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
