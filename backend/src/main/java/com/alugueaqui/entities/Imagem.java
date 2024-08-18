package com.alugueaqui.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "IMAGENS")
public class Imagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "postagem_veiculo_id")
    private PostagemVeiculo postagemVeiculo;

    @ManyToOne
    @JoinColumn(name = "postagem_imovel_id")
    private PostagemImovel postagemImovel;
}