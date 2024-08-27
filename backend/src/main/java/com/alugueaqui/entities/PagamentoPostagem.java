package com.alugueaqui.entities;

import com.alugueaqui.enums.PagamentoTipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "pagamentos")
public class PagamentoPostagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "pagamento_tipo", nullable = false, length = 50)
    private PagamentoTipo pagamentoTipo;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postagem_id", nullable = false)
    private Postagem postagem;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "stregistro")
    private Integer statusRegistro = 1;
}