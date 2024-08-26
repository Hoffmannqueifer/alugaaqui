package com.alugueaqui.web.controllers;

import com.alugueaqui.entities.Funcionario;
import com.alugueaqui.entities.Usuario;
import com.alugueaqui.servicies.FuncionarioService;
import com.alugueaqui.servicies.UsuarioService;
import com.alugueaqui.web.dtos.*;
import com.alugueaqui.web.dtos.creates.FuncionarioCreateDto;
import com.alugueaqui.web.dtos.creates.UsuarioCreateDto;
import com.alugueaqui.web.dtos.mappers.FuncionarioMapper;
import com.alugueaqui.web.dtos.mappers.UsuarioMapper;
import com.alugueaqui.web.dtos.responses.FuncionarioResponseDto;
import com.alugueaqui.web.dtos.responses.UsuarioResponseDto;
import com.alugueaqui.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos de cadastro, edição e leitura")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final FuncionarioService funcionarioService;

    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            UsuarioCreateDto.class))),
            @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no sistema",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            ErrorMessage.class)))
        }
    )
    @PostMapping("/adm")
    public ResponseEntity<UsuarioResponseDto> createUserAdmin(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @PostMapping("/funcionario")
    public ResponseEntity<FuncionarioResponseDto> createUserFuncionario(@Valid @RequestBody FuncionarioCreateDto createDto) {
        Funcionario funcionario = funcionarioService.criarFuncionario(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(FuncionarioMapper.toDto(funcionario));
    }

    @Operation(summary = "Recuperar um usuário pelo id", description = "Requisição exige um Bearer Token. Acesso " +
            "restrito a usuários com perfil ADMIN ou FUNCIONARIO. FUNCIONARIO apenas quando esteja acessando seu próprio" +
            " recurso",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                                    UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                                    ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                                    ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('FUNCIONARIO') AND #id == authentication.principal.id)")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Atualizar senha", description = "Requisição exige um Bearer Token. Acesso " +
            "restrito a usuários com perfil ADMIN ou FUNCIONARIO que esteja acessando seu próprio recurso",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Senha incorreta",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                                    ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                                    ErrorMessage.class)))
            }
    )
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') AND #id == authentication.principal.id")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid
                                                  @RequestBody UsuarioSenhaDto usuarioSenhaDto) {
        usuarioService.editarSenha(id,usuarioSenhaDto.getSenhaAtual(),
                usuarioSenhaDto.getNovaSenha(), usuarioSenhaDto.getConfirmarSenha());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todos os usuários", description = "Requisição exige um Bearer Token. Acesso " +
            "restrito a usuários com perfil ADMIN",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista com todos os usuários cadastrados",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                                    UsuarioCreateDto.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                                    ErrorMessage.class)))
            }
    )
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }

}
