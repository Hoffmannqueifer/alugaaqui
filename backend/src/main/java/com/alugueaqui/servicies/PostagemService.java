package com.alugueaqui.servicies;

import com.alugueaqui.entities.*;
import com.alugueaqui.repositories.PostagemRepository;
import com.alugueaqui.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
                        postagem.getItem()));
            }
            postagem.setItem(item);

            Cliente cliente =
                    clienteService.buscarPorCpf(StringUtils.getStringNumerico(postagem.getCliente().getCpf()));
            if (cliente == null) {
                throw new NoSuchElementException(String.format("Cliente com CPF '%s' não encontrado.",
                        postagem.getCliente().getCpf()));
            }
            postagem.setCliente(cliente);

            if (postagem.getImagens() != null && !postagem.getImagens().isEmpty()) {
                List<Imagem> imagens = processarImagens(postagem, postagem.getImagens());
                postagem.setImagens(imagens);
            }

            if (postagem.getPagamentos() != null && !postagem.getPagamentos().isEmpty()) {
                List<PagamentoPostagem> pagamentos = processarPagamentos(postagem, postagem.getPagamentos());
                postagem.setPagamentos(pagamentos);
            }

            return postagemRepository.save(postagem);

        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException(String.format("Ocorreu um erro ao salvar a Postagem '%s' ",
                    postagem.getTitulo()));
        }
    }

    private List<Imagem> processarImagens(Postagem postagem, List<Imagem> imagens) {
        if (imagens != null && !imagens.isEmpty()) {
            List<Imagem> listImagens = new ArrayList<>();
            for (Imagem imagem : imagens) {
                Imagem novaImagem = new Imagem();
                if(imagem.getImageData() != null) {
                    novaImagem.setImageData(imagem.getImageData());
                }
                if(imagem.getImageUrl() != null) {
                    novaImagem.setImageUrl(imagem.getImageUrl());
                }
                novaImagem.setPostagem(postagem);
                listImagens.add(novaImagem);
            }
            return imagemService.salvarVariasImagens(listImagens);
        }
        return new ArrayList<>();
    }


    private List<PagamentoPostagem> processarPagamentos(Postagem postagem, List<PagamentoPostagem> pagamentos) {
        List<PagamentoPostagem> listPagamentos = new ArrayList<>();
        for (PagamentoPostagem pagamentoPostagem : pagamentos) {
            PagamentoPostagem pagamento = new PagamentoPostagem();
            pagamento.setData(pagamentoPostagem.getData());
            pagamento.setValor(pagamentoPostagem.getValor());
            pagamento.setPagamentoTipo(pagamentoPostagem.getPagamentoTipo());
            pagamento.setStatus(pagamentoPostagem.getStatus());
            pagamento.setObservacao(pagamentoPostagem.getObservacao());
            pagamento.setPostagem(postagem);  // Associando a postagem ao pagamento
            listPagamentos.add(pagamento);
        }
        return pagamentoPostagemService.salvarPagamentoPostagem(listPagamentos);
    }
}