package com.alugueaqui.entities;

import com.alugueaqui.enums.CondicaoVendaTipo;
import com.alugueaqui.enums.EstadoImovelTipo;
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
@Table(name = "POSTAGENS_IMOVEIS")
@EntityListeners(AuditingEntityListener.class)
public class PostagemImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_imovel", nullable = false)
    private String tipoImovel;

    @Column(name = "area")
    private Double area;

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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado_imovel", nullable = false)
    private EstadoImovelTipo estadoImovel;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "condicao_venda", nullable = false)
    private CondicaoVendaTipo condicaoVenda;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco enderecoImovel;

    @OneToMany(mappedBy = "postagemImovel", cascade = CascadeType.ALL, orphanRemoval = true)
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