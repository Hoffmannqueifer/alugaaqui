package com.alugueaqui.entities;

import com.alugueaqui.enums.EstadoItemTipo;
import com.alugueaqui.enums.ItemNegociadoTipo;
import com.alugueaqui.enums.NegociacaoTipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "ITENS")
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(name = "negociacao_tipo")
    private NegociacaoTipo negociaoTipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_item_tipo", nullable = false)
    private EstadoItemTipo estadoItemTipo;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "stregistro")
    private Integer statusRegistro = 1;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_negociado", nullable = false)
    private ItemNegociadoTipo itemNegociadoTipo;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

}
