package com.alugueaqui.servicies;

import com.alugueaqui.entities.PagamentoPostagem;
import com.alugueaqui.repositories.PagamentoPostagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PagamentoPostagemService {

    private final PagamentoPostagemRepository pagamentoPostagemRepository;

    public PagamentoPostagem salvarPagamentoPostagem(PagamentoPostagem pagamentoPostagem) {
        return pagamentoPostagemRepository.save(pagamentoPostagem);
    }

    public List<PagamentoPostagem> salvarPagamentoPostagem(List<PagamentoPostagem> pagamentos) {
        return pagamentoPostagemRepository.saveAll(pagamentos);
    }
}
