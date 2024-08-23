package com.alugueaqui.servicies;

import com.alugueaqui.entities.Funcionario;
import com.alugueaqui.entities.Usuario;
import com.alugueaqui.exceptions.CpfUniqueViolationException;
import com.alugueaqui.exceptions.EntityNotFoundException;
import com.alugueaqui.repositories.FuncionarioRepository;
import com.alugueaqui.repositories.projections.FuncionarioProjection;
import com.alugueaqui.util.Constantes;
import com.alugueaqui.web.dtos.FuncionarioCreateDto;
import com.alugueaqui.web.dtos.mappers.FuncionarioMapper;
import com.alugueaqui.web.dtos.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;;
    private final UsuarioService usuarioService;

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        try{
            funcionarioRepository.findByCpf(funcionario.getCpf()).ifPresent(
                    f -> {
                        throw new CpfUniqueViolationException(String.format("CPF '%s' não pode ser cadastrado, já existe no " +
                                "sistema", funcionario.getCpf()));
                    });

            return funcionarioRepository.save(funcionario);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException(String.format("Funcionario '%s' não pode ser cadastrado", funcionario.getNome()));
        }
    }

    @Transactional
    public Funcionario criarFuncionario(FuncionarioCreateDto funcionarioCreateDto) {
        Usuario usuario = UsuarioMapper.toUsuario(funcionarioCreateDto.getUsuario());
        usuario.setRole(Usuario.Role.ROLE_FUNCIONARIO);
        usuario.setStatusRegistro(Constantes.ativo);
        usuario = usuarioService.salvar(usuario);

        Funcionario funcionario = FuncionarioMapper.toFuncionario(funcionarioCreateDto);
        funcionario.setUsuario(usuario);
        funcionario.setEmail(usuario.getUsername());
        funcionario.setStatusRegistro(Constantes.ativo);

        return salvar(funcionario);
    }

    @Transactional(readOnly = true)
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Funcionario id=%s não encontrado no sistema", id)));
    }

    @Transactional(readOnly = true)
    public Page<FuncionarioProjection> buscarTodos(Pageable pageable) {
        return funcionarioRepository.findAllPageable(pageable);
    }

    @Transactional(readOnly = true)
    public Funcionario buscarPorUsuarioId(Long id) {
        return funcionarioRepository.findByUsuarioId(id);
    }

    @Transactional(readOnly = true)
    public Funcionario buscarPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf).orElseThrow(
                () -> new EntityNotFoundException(String.format("Funcionario com CPF '%s' não encontrado no sistema", cpf)));
    }
}
