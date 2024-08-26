package com.alugueaqui.web.controllers;

import com.alugueaqui.entities.Funcionario;
import com.alugueaqui.jwt.JwtUserDetails;
import com.alugueaqui.repositories.projections.FuncionarioProjection;
import com.alugueaqui.servicies.FuncionarioService;
import com.alugueaqui.servicies.UsuarioService;
import com.alugueaqui.web.dtos.creates.FuncionarioCreateDto;
import com.alugueaqui.web.dtos.responses.FuncionarioResponseDto;
import com.alugueaqui.web.dtos.PageableDto;
import com.alugueaqui.web.dtos.creates.UsuarioCreateDto;
import com.alugueaqui.web.dtos.mappers.FuncionarioMapper;
import com.alugueaqui.web.dtos.mappers.PageableMapper;
import com.alugueaqui.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/funcionarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final UsuarioService usuarioService;

    @Operation(summary = "Criar um novo funcionario", description = "Recurso para criar um novo funcionario Requisção exige " +
            "o uso de um bearer token. Acesso restrito a usuários com perfil FUNCIONARIO.",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation =
                                    UsuarioCreateDto.class))),
                    @ApiResponse(responseCode = "409", description = "Funcionario CPF já possui cadastro no sistema",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation =
                                    ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation =
                                    ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permitido ao perfil de ADMIN",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation =
                                    ErrorMessage.class)))

            }
    )
    @PostMapping
    @PreAuthorize("hasRole('FUNCIONARIO')")
    public ResponseEntity<FuncionarioResponseDto> create(@RequestBody @Valid FuncionarioCreateDto funcionarioCreateDto,
                                                    @AuthenticationPrincipal JwtUserDetails userDetails) {

        Funcionario funcionario = FuncionarioMapper.toFuncionario(funcionarioCreateDto);
        funcionario.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
        funcionarioService.salvar(funcionario);
        return ResponseEntity.status(201).body(FuncionarioMapper.toDto(funcionario));
    }

    @Operation(summary = "Buscar um funcionario", description = "Recurso para buscar um funcionario pelo ID. Requisção exige " +
            "o uso de um bearer token. Acesso restrito a usuários com perfil 'ADMIN'.",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso localizado com sucesso",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation =
                                    UsuarioCreateDto.class))),
                    @ApiResponse(responseCode = "404", description = "Funcionario não encontrado",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation =
                                    ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permitido ao perfil de FUNCIONARIO",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation =
                                    ErrorMessage.class)))

            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FuncionarioResponseDto> getById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(FuncionarioMapper.toDto(funcionario));
    }

    @Operation(summary = "Recuperar lista de funcionarios",
            description = "Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN' ",
            security = @SecurityRequirement(name = "security"),
            parameters = {
                    @Parameter(in = QUERY, name = "page",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")),
                            description = "Representa a página retornada"
                    ),
                    @Parameter(in = QUERY, name = "size",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "5")),
                            description = "Representa o total de elementos por página"
                    ),
                    @Parameter(in = QUERY, name = "sort", hidden = true,
                            array = @ArraySchema(schema = @Schema(type = "string", defaultValue = "nome,asc")),
                            description = "Representa a ordenação dos resultados. Aceita multiplos critérios de " +
                                    "ordenação. Ex: 'nome,asc', 'cpf,desc'")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = PageableDto.class))
                    ),
                    @ApiResponse(responseCode = "403", description = "Recurso não permito ao perfil de FUNCIONARIO",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDto> getAll(@Parameter(hidden = true) @PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        Page<FuncionarioProjection> funcionarios = funcionarioService.buscarTodos(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(funcionarios));
    }

    @Operation(summary = "Recuperar dados do funcionario autenticado",
            description = "Requisição exige uso de um bearer token. Acesso restrito a Role='FUNCIONARIO'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = FuncionarioResponseDto.class))
                    ),
                    @ApiResponse(responseCode = "403", description = "Recurso não permito ao perfil de ADMIN",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
    @GetMapping("/detalhes")
    @PreAuthorize("hasRole('FUNCIONARIO')")
    public ResponseEntity<FuncionarioResponseDto> getAll(@AuthenticationPrincipal JwtUserDetails userDetails) {
        Funcionario funcionario = funcionarioService.buscarPorUsuarioId(userDetails.getId());
        return ResponseEntity.ok(FuncionarioMapper.toDto(funcionario));
    }
}
