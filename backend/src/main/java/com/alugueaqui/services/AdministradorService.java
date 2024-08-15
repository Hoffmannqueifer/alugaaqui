package com.alugueaqui.services;

import com.alugueaqui.entities.Administrador;
import com.alugueaqui.enums.Perfil;
import com.alugueaqui.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Transactional(readOnly = true)
    public Administrador buscarPorUsername(String username) {
        return null;
    }

    @Transactional(readOnly = true)
    public Perfil buscarRolePorUsername(String username) {
        return null;
    }
}
