package com.alugueaqui.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "TB_IMAGENS")
public class Imagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagem")
    private Long id;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "postagem_id", nullable = true)
    private Postagem postagem;

    @Column(name = "stregistro")
    private Integer statusRegistro = 1;
}