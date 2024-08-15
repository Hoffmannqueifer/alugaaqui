package com.alugueaqui.jwt;

import com.alugueaqui.entities.Administrador;
import com.alugueaqui.enums.Perfil;
import com.alugueaqui.services.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final AdministradorService administradorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrador usuario = administradorService.buscarPorUsername(username);
        return new JwtUserDetails((Administrador) usuario);
    }

    public JwtToken getTokenAuthenticated(String username) {
        Perfil role = administradorService.buscarRolePorUsername(username);
        return JwtUtils.createToken(username, role.getDescricao().substring("ROLE_".length()));
    }
}
