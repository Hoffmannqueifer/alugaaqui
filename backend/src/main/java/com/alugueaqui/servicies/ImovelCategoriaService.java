package com.alugueaqui.servicies;

import com.alugueaqui.entities.ImovelCategoria;
import com.alugueaqui.repositories.ImovelCategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImovelCategoriaService {

    private final ImovelCategoriaRepository imovelCategoriaRepository;

    public List<ImovelCategoria> buscarTodos() {
        return imovelCategoriaRepository.findAll();
    }
}
