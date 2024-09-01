package com.alugueaqui.servicies;

import com.alugueaqui.entities.PagamentoPostagem;
import com.alugueaqui.repositories.PagamentoPostagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PagamentoPostagemService {

    private final PagamentoPostagemRepository pagamentoPostagemRepository;

    @Transactional
    public PagamentoPostagem salvarPagamentoPostagem(PagamentoPostagem pagamentoPostagem) {
        return pagamentoPostagemRepository.save(pagamentoPostagem);
    }

    @Transactional
    public List<PagamentoPostagem> salvarPagamentoPostagem(List<PagamentoPostagem> pagamentos) {
        return pagamentoPostagemRepository.saveAll(pagamentos);
    }

}
