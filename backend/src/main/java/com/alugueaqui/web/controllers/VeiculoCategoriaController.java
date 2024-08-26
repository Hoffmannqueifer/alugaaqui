package com.alugueaqui.web.controllers;

import com.alugueaqui.entities.ImovelCategoria;
import com.alugueaqui.servicies.ImovelCategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Categoria Veículo", description = "Contém todas as operações relativas aos recursos de leitura")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/categorias/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoCategoriaController {

    private final ImovelCategoriaService imovelCategoriaService;

    @GetMapping()
    @PreAuthorize("hasRole('FUNCIONARIO')")
    public ResponseEntity<List<ImovelCategoria>> getAll() {
        List<ImovelCategoria> imoveisCategoria = imovelCategoriaService.buscarTodos();
        return ResponseEntity.ok(imoveisCategoria);
    }
}

