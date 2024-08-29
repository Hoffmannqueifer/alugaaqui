package com.alugueaqui.servicies;

import com.alugueaqui.entities.Endereco;
import com.alugueaqui.entities.Item;
import com.alugueaqui.repositories.EnderecoRepository;
import com.alugueaqui.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final EnderecoRepository enderecoRepository;


    @Transactional
    public Item salvar(Item item) {
        validateCamposItem(item);
        return itemRepository.save(item);
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Item '%s'  não encontrado: ", id)));
    }

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void deletarItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Item '%s' não encontrado: " , id));
        }
        itemRepository.deleteById(id);
    }

    public List<Item> buscarTodosItens() {
        return itemRepository.findAll();
    }


    public void validateCamposItem(Item item) {

        if (item.getQuantidade() == null) {
            throw new IllegalArgumentException ("Quantidade não pode ser nulo.");
        }
        if (item.getValorUnitario() == null) {
            throw new IllegalArgumentException  ("Valor Unitário não pode ser nulo.");
        }
        if (item.getItemNegociadoTipo() == null) {
            throw new IllegalArgumentException  ("Tipo de item negociado não pode ser nulo.");
        }
        if (item.getEstadoItemTipo() == null) {
            throw new IllegalArgumentException  ("Estado do item não pode ser nulo.");
        }
        if (item.getEndereco() == null) {
            throw new IllegalArgumentException  ("Endereço do item negociado não pode ser nulo.");
        }

        if (item.getValorParcela() != null && item.getValorParcela() < 0) {
            throw new IllegalArgumentException  ("Valor da parcela deve ser positivo.");
        }
        if (item.getQuantidadeParcelas() != null && item.getQuantidadeParcelas() < 0) {
            throw new IllegalArgumentException  ("Quantidade de parcelas deve ser positiva.");
        }
        if (item.getValorTotalParcelado() != null && item.getValorTotalParcelado() < 0) {
            throw new IllegalArgumentException  ("Valor total parcelado deve ser positivo.");
        }
    }

    @Transactional(readOnly = true)
    public Page<Item> buscarTodos(Pageable pageable) {
        return itemRepository.findAllPageable(pageable);
    }
}