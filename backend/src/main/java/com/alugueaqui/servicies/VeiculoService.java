package com.alugueaqui.servicies;

import com.alugueaqui.entities.Veiculo;
import com.alugueaqui.exceptions.EntityNotFoundException;
import com.alugueaqui.repositories.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public Veiculo salvar(Veiculo veiculo) {
        try{

            validarCamposVeiculo(veiculo);

            return veiculoRepository.save(veiculo);

        } catch (DataIntegrityViolationException exception){
            throw new DataIntegrityViolationException(String.format("Ocorreu um erro ao salvar o Veiculo '%s' ", veiculo.getModelo()));
        }
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Veiculo id=%s não encontrado no sistema", id)));
    }

    private void validarCamposVeiculo(Veiculo veiculo) {
        if (veiculo.getMarca() == null || veiculo.getMarca().isEmpty()) {
            throw new IllegalArgumentException("Marca do veículo não pode ser nula ou vazia.");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo não pode ser nulo ou vazio.");
        }
        if (veiculo.getAnoFabricacao() != null && veiculo.getAnoFabricacao() <= 0) {
            throw new IllegalArgumentException("Ano de fabricação deve ser positivo.");
        }
        if (veiculo.getKilometragem() != null && veiculo.getKilometragem() < 0) {
            throw new IllegalArgumentException("Quilometragem deve ser não negativa.");
        }
        if (veiculo.getCor() != null && veiculo.getCor().isEmpty()) {
            throw new IllegalArgumentException("Cor do veículo não pode ser vazia.");
        }
        if (veiculo.getTipoCombustivel() != null && veiculo.getTipoCombustivel().isEmpty()) {
            throw new IllegalArgumentException("Tipo de combustível não pode ser vazio.");
        }
        if (veiculo.getNumPortas() != null && veiculo.getNumPortas() < 0) {
            throw new IllegalArgumentException("Número de portas deve ser não negativo.");
        }
    }
}
