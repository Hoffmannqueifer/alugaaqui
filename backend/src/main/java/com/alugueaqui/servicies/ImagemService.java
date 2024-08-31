package com.alugueaqui.servicies;

import com.alugueaqui.entities.Imagem;
import com.alugueaqui.repositories.ImagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;

    @Transactional
    public List<Imagem> salvarVariasImagens(List<Imagem> imagens) {
        return imagemRepository.saveAll(imagens);
    }

    @Transactional(readOnly = true)
    public List<Imagem> buscarTodasImagensPorPostagemId(Long postagemId) {
        return imagemRepository.findByPostagemId(postagemId);
    }
}
