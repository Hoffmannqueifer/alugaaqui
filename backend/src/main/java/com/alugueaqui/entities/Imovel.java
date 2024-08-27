package com.alugueaqui.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "IMOVEIS")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comprimento")
    private Double comprimento;

    @Column(name = "largura")
    private Double largura;

    @Column(name = "quartos")
    private Integer quartos;

    @Column(name = "banheiros")
    private Integer banheiros;

    @Column(name = "vagas_garagem")
    private Integer vagasGaragem;

    @Column(name = "ano_construcao")
    private Integer anoConstrucao;

    @Column(name = "condominio")
    private Double condominio;

    @Column(name = "caracteristicas")
    private String caracteristicas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imovel_categoria_id", nullable = false)
    private ImovelCategoria imovelCategoria;

    @Column(name = "stregistro")
    private Integer statusRegistro = 1;
}
