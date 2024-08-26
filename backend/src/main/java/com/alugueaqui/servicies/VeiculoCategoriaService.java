package com.alugueaqui.servicies;

import com.alugueaqui.entities.VeiculoCategoria;
import com.alugueaqui.repositories.VeiculoCategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VeiculoCategoriaService {

    private final VeiculoCategoriaRepository veiculoCategoriaRepository;

    public List<VeiculoCategoria> buscarTodos() {
        return veiculoCategoriaRepository.findAll();
    }
}
