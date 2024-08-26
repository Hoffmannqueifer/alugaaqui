package com.alugueaqui.web.controllers;

import com.alugueaqui.entities.VeiculoCategoria;
import com.alugueaqui.servicies.VeiculoCategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Categoria Imóvel", description = "Contém todas as operações relativas aos recursos de cadastro, edição e leitura")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/categorias/imoveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImovelCategoriaController {

    private final VeiculoCategoriaService veiculoCategoriaService;

    @GetMapping()
    @PreAuthorize("hasRole('FUNCIONARIO')")
    public ResponseEntity<List<VeiculoCategoria>> getAll() {
        List<VeiculoCategoria> veiculosCategoria = veiculoCategoriaService.buscarTodos();
        return ResponseEntity.ok(veiculosCategoria);
    }
}
