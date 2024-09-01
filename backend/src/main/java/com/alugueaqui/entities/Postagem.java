package com.alugueaqui.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "TB_POSTAGENS")
@EntityListeners(AuditingEntityListener.class)
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postagem")
    private Long id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "descricao", nullable = false, length = 300)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Imagem> imagens;

    @OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PagamentoPostagem> pagamentos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "data_encerramento")
    private LocalDate dataFim;

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

    @Column(name = "stregistro")
    private Integer statusRegistro = 1;

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
        for (Imagem imagem : imagens) {
            imagem.setPostagem(this); // relação bidirecional
        }
    }

    public void setPagamentos(List<PagamentoPostagem> pagamentos) {
        this.pagamentos = pagamentos;
        for (PagamentoPostagem pagamento : pagamentos) {
            pagamento.setPostagem(this); // relação bidirecional
        }
    }
}