package com.alugueaqui.entities;

import com.alugueaqui.enums.CondicaoVendaTipo;
import com.alugueaqui.enums.EstadoItemTipo;
import com.alugueaqui.enums.ItemNegociadoTipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "ITENS")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor_unitario", nullable = false)
    private Double valorUnitario;

    @Column(name = "valor_parcela")
    private Double valorParcela;

    @Column(name = "quantidade_parcelas")
    private Integer quantidadeParcelas;

    @Column(name = "valor_total_parcelado")
    private Double valorTotalParcelado;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_negociado", nullable = false)
    private ItemNegociadoTipo itemNegociadoTipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicao_venda_tipo")
    private CondicaoVendaTipo condicaoVendaTipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_item_tipo", nullable = false)
    private EstadoItemTipo estadoItemTipo;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    @Column(name = "stRegistro", columnDefinition = "integer default 1")
    private Integer statusRegistro;
}