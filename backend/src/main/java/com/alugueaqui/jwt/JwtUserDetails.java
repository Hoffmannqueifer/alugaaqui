package com.alugueaqui.jwt;

import com.alugueaqui.entities.Administrador;
import com.alugueaqui.enums.Perfil;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private Administrador usuario;

    public JwtUserDetails(Administrador usuario) {
        super(usuario.getEmail(), usuario.getSenha(), AuthorityUtils.createAuthorityList(
                Perfil.ADMIN.getDescricao()));
        this.usuario = usuario;
    }

    public Integer getId() {
        return usuario.getId();
    }

    public String getRole() {
        return Perfil.ADMIN.getDescricao();
    }


}
