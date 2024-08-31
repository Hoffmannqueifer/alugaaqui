package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.entities.PagamentoPostagem;
import com.alugueaqui.web.dtos.creates.PagamentoPostagemCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagamentoMapper {

    public static PagamentoPostagem toPagamento(PagamentoPostagemCreateDto createDto) {
        return new ModelMapper().map(createDto, PagamentoPostagem.class);
    }

    public static List<PagamentoPostagem> toPagamentos(List<PagamentoPostagemCreateDto> pagamentos) {
        return pagamentos.stream()
                .map(PagamentoMapper::toPagamento)
                .collect(Collectors.toList());
    }
}
