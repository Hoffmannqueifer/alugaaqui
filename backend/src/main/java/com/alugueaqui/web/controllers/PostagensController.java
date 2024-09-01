package com.alugueaqui.web.controllers;

import com.alugueaqui.entities.Postagem;
import com.alugueaqui.enums.PagamentoTipo;
import com.alugueaqui.servicies.ImagemService;
import com.alugueaqui.servicies.PagamentoPostagemService;
import com.alugueaqui.servicies.PostagemService;
import com.alugueaqui.web.dtos.PageableDto;
import com.alugueaqui.web.dtos.creates.PostagemCreateDto;
import com.alugueaqui.web.dtos.mappers.ImagemMapper;
import com.alugueaqui.web.dtos.mappers.PagamentoMapper;
import com.alugueaqui.web.dtos.mappers.PageableMapper;
import com.alugueaqui.web.dtos.mappers.PostagemMapper;
import com.alugueaqui.web.dtos.responses.PostagemResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Postagens", description = "Contém todas as operações relativas aos recursos de cadastro, edição e leitura")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/postagens", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostagensController {

    private final PostagemService postagemService;
    private final ImagemService imagemService;
    private final PagamentoPostagemService pagamentoPostagemService;

    @PostMapping
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    public ResponseEntity<PostagemResponseDto> create(@RequestBody @Valid PostagemCreateDto postagemCreateDto) {
        Postagem postagem = PostagemMapper.toPostagem(postagemCreateDto);

        converterDtoToImagem(postagem, postagemCreateDto);
        converterDtoToPagamento(postagem, postagemCreateDto);

        Postagem postagemSalvo = postagemService.criarPostagem(postagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostagemMapper.toDto(postagemSalvo));
    }

    private void converterDtoToImagem(Postagem postagem, PostagemCreateDto postagemCreateDto) {
        if (postagemCreateDto.getImagens() != null) {
            postagem.setImagens(ImagemMapper.toImagens(postagemCreateDto.getImagens()));
        }
    }

    private void converterDtoToPagamento(Postagem postagem, PostagemCreateDto postagemCreateDto) {
        if (postagemCreateDto.getPagamento() != null) {
            postagem.setPagamentos(PagamentoMapper.toPagamentos(postagemCreateDto.getPagamento()));
        }
    }

    @GetMapping("/pagamento-tipos")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    public List<PagamentoTipo> getListPagamentoTipo() {
        return Arrays.asList(PagamentoTipo.values());
    }


    @GetMapping
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    public ResponseEntity<PageableDto> getAll(
            @Parameter(hidden = true)
            @PageableDefault(size = 5, sort = {"titulo"}) Pageable pageable) {

        Page<Postagem> postagens = postagemService.buscarTodos(pageable);
        Page<PostagemResponseDto> dtoPage = postagens.map(PostagemMapper::toDto);

        return ResponseEntity.ok(PageableMapper.toDto(dtoPage));
    }
}
