package com.alugueaqui.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "VEICULOS")
public class Veiculo extends Item {

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Column(name = "kilometragem")
    private Double kilometragem;

    @Column(name = "cor")
    private String cor;

    @Column(name = "tipo_combustivel")
    private String tipoCombustivel;

    @Column(name = "num_portas")
    private Integer numPortas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_categoria_id", nullable = false)
    private VeiculoCategoria veiculoCategoria;
}