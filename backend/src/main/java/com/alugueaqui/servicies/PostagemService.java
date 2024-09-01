package com.alugueaqui.servicies;

import com.alugueaqui.entities.*;
import com.alugueaqui.repositories.PostagemRepository;
import com.alugueaqui.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;
    private final ItemService itemService;
    private final ImagemService imagemService;
    private final ClienteService clienteService;
    private final PagamentoPostagemService pagamentoPostagemService;

    @Transactional
    public Postagem criarPostagem(Postagem postagem) {

        try {
            Item item = itemService.findItemById(postagem.getItem().getId());
            if (item == null) {
                throw new NoSuchElementException(String.format("Item com ID '%s' não encontrado.",
                        postagem.getItem().getId()));
            }
            postagem.setItem(item);

            Cliente cliente = clienteService.buscarPorCpf(StringUtils.getStringNumerico(postagem.getCliente().getCpf()));
            if (cliente == null) {
                throw new NoSuchElementException(String.format("Cliente com CPF '%s' não encontrado.",
                        postagem.getCliente().getCpf()));
            }
            postagem.setCliente(cliente);

            processarImagens(postagem);
            processarPagamentos(postagem);

            return postagemRepository.save(postagem);

        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException(String.format("Ocorreu um erro ao salvar a Postagem '%s' ",
                    postagem.getTitulo()));
        }
    }

    private void processarImagens(Postagem postagem) {
        if (postagem.getImagens() != null) {
            for (Imagem imagem : postagem.getImagens()) {
                if (imagem.getImageData() != null) {
                    imagem.setImageData(imagem.getImageData());
                }
                if (imagem.getImageUrl() != null) {
                    imagem.setImageUrl(imagem.getImageUrl());
                }
                imagem.setPostagem(postagem);
            }
        }
    }


    private void processarPagamentos(Postagem postagem) {
        if(postagem.getPagamentos() != null) {
            for (PagamentoPostagem pagamentoPostagem : postagem.getPagamentos()) {
                pagamentoPostagem.setData(pagamentoPostagem.getData());
                pagamentoPostagem.setValor(pagamentoPostagem.getValor());
                pagamentoPostagem.setPagamentoTipo(pagamentoPostagem.getPagamentoTipo());
                pagamentoPostagem.setStatus(pagamentoPostagem.getStatus());
                pagamentoPostagem.setObservacao(pagamentoPostagem.getObservacao());
                pagamentoPostagem.setPostagem(postagem);
            }
        }
    }

    @Transactional(readOnly = true)
    public Page<Postagem> buscarTodos(Pageable pageable) {
        return postagemRepository.findAllPageable(pageable);
    }
}