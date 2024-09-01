package com.alugueaqui.web.controllers;

import com.alugueaqui.entities.Item;
import com.alugueaqui.enums.EstadoItemTipo;
import com.alugueaqui.enums.ItemNegociadoTipo;
import com.alugueaqui.enums.NegociacaoTipo;
import com.alugueaqui.servicies.ItemService;
import com.alugueaqui.web.dtos.PageableDto;
import com.alugueaqui.web.dtos.creates.ItemCreateDto;
import com.alugueaqui.web.dtos.mappers.ItemMapper;
import com.alugueaqui.web.dtos.mappers.PageableMapper;
import com.alugueaqui.web.dtos.responses.ItemResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Itens", description = "Contém todas as operações relativas aos recursos de cadastro, edição e leitura")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/itens", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/veiculos")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    public ResponseEntity<ItemResponseDto> create(@RequestBody @Valid ItemCreateDto itemCreateDto) {

        Item itemSalvo = itemService.salvar(ItemMapper.toItem(itemCreateDto));
        return ResponseEntity.status(201).body(ItemMapper.toDto(itemSalvo));
    }

    @GetMapping("/item-negociado-tipos")
    public List<ItemNegociadoTipo> getListItemNegociadoTipos() {
        return Arrays.asList(ItemNegociadoTipo.values());
    }

    @GetMapping("/estado-item-tipos")
    public List<EstadoItemTipo> getListEstadoItemTipo() {
        return Arrays.asList(EstadoItemTipo.values());
    }

    @GetMapping("/negociacao-tipos")
    public List<NegociacaoTipo> getListNegocioacoTipo() {
        return Arrays.asList(NegociacaoTipo.values());
    }

    @GetMapping
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    public ResponseEntity<PageableDto> getAll(
            @Parameter(hidden = true)
            @PageableDefault(size = 5, sort = {"quantidade"}) Pageable pageable) {

        Page<Item> itens = itemService.buscarTodos(pageable);
        Page<ItemResponseDto> dtoPage = itens.map(ItemMapper::toDto);

        return ResponseEntity.ok(PageableMapper.toDto(dtoPage));
    }
}
