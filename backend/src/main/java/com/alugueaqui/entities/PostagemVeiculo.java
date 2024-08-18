package com.alugueaqui.entities;

import com.alugueaqui.enums.CondicaoVendaTipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "POSTAGENS_VEICULOS")
@EntityListeners(AuditingEntityListener.class)
public class PostagemVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "condicao_venda", nullable = false)
    private CondicaoVendaTipo condicaoVenda;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco enderecoVeiculo;

    @OneToMany(mappedBy = "postagemVeiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> imagens = new ArrayList<>();

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @CreatedBy
    @Column(name = "criado_por")
    private String criadoPor;

    @LastModifiedBy
    @Column(name = "modificado_por")
    private String modificadoPor;
}