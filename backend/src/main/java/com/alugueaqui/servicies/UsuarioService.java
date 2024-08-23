package com.alugueaqui.servicies;

import com.alugueaqui.entities.Usuario;
import com.alugueaqui.exceptions.EntityNotFoundException;
import com.alugueaqui.exceptions.PasswordInvalidException;
import com.alugueaqui.exceptions.UsernameUniqueViolationException;
import com.alugueaqui.repositories.UsuarioRepository;
import com.alugueaqui.util.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuario.setStatusRegistro(Constantes.ativo);
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException exception){
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado",
                    usuario.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado.", id))
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmarSenha) {

        if(!novaSenha.equals(confirmarSenha)){
            throw new PasswordInvalidException("Nova senha e confirmação de senha são diferentes.");
        }

        Usuario user = buscarPorId(id);

        if(!passwordEncoder.matches(senhaAtual, user.getPassword())){
            throw new PasswordInvalidException("Senha atual incorreta.");
        }

        if(passwordEncoder.matches(novaSenha, user.getPassword())){
            throw new PasswordInvalidException("Nova senha igual à senha atual.");
        }
        user.setPassword(passwordEncoder.encode(novaSenha));
        return usuarioRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário com '%s' não encontrado"))
        );
    }

    @Transactional(readOnly = true)
    public Usuario.Role buscarRolePorUsername(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }
}
