package com.alugueaqui.servicies;

import com.alugueaqui.entities.Imovel;
import com.alugueaqui.exceptions.EntityNotFoundException;
import com.alugueaqui.repositories.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImovelService {

    private final ImovelRepository imovelRepository;

    @Transactional
    public Imovel criarImovel(Imovel imovel) {

        try {

            validarCamposImovel(imovel);
            return imovelRepository.save(imovel);

        } catch (DataIntegrityViolationException exception) {
            throw new IllegalArgumentException("Ocorreu um erro ao salvar o imóvel.");
        }
    }

    public Imovel buscarPorId(Long id) {
        return imovelRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Imóvel id=%s não encontrado no sistema", id)));
    }

    public List<Imovel> buscarTodos() {
        return imovelRepository.findAll();
    }

    private void validarCamposImovel(Imovel imovel) {
        if (imovel.getComprimento() != null && imovel.getComprimento() <= 0) {
            throw new IllegalArgumentException("Comprimento do imóvel deve ser positiva.");
        }
        if (imovel.getLargura() != null && imovel.getLargura() <= 0) {
            throw new IllegalArgumentException("Largura do imóvel deve ser positiva.");
        }
        if (imovel.getQuartos() != null && imovel.getQuartos() < 0) {
            throw new IllegalArgumentException("Número de quartos deve ser não negativo.");
        }
        if (imovel.getBanheiros() != null && imovel.getBanheiros() < 0) {
            throw new IllegalArgumentException("Número de banheiros deve ser não negativo.");
        }
        if (imovel.getVagasGaragem() != null && imovel.getVagasGaragem() < 0) {
            throw new IllegalArgumentException("Número de vagas de garagem deve ser não negativo.");
        }
        if (imovel.getAnoConstrucao() != null && imovel.getAnoConstrucao() <= 0) {
            throw new IllegalArgumentException("Ano de construção deve ser positivo.");
        }
        if (imovel.getCondominio() != null && imovel.getCondominio() < 0) {
            throw new IllegalArgumentException("Valor do condomínio deve ser positivo.");
        }
    }
}
