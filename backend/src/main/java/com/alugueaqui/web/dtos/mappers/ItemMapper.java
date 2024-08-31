package com.alugueaqui.web.dtos.mappers;

import com.alugueaqui.entities.Endereco;
import com.alugueaqui.entities.Item;
import com.alugueaqui.entities.Veiculo;
import com.alugueaqui.entities.VeiculoCategoria;
import com.alugueaqui.enums.EstadoItemTipo;
import com.alugueaqui.enums.ItemNegociadoTipo;
import com.alugueaqui.enums.NegociacaoTipo;
import com.alugueaqui.web.dtos.creates.ItemCreateDto;
import com.alugueaqui.web.dtos.responses.ItemResponseDto;
import org.modelmapper.ModelMapper;

public class ItemMapper {

    public static Item toItem(ItemCreateDto createDto) {
        Item item = new Item();

        item.setQuantidade(createDto.getQuantidade());
        item.setValorUnitario(createDto.getValorUnitario());
        item.setValorParcela(createDto.getValorParcela());
        item.setQuantidadeParcelas(createDto.getQuantidadeParcelas());
        item.setValorTotalParcelado(createDto.getValorTotalParcelado());

        item.setNegociaoTipo(NegociacaoTipo.valueOf(createDto.getNegociaoTipo()));
        item.setEstadoItemTipo(EstadoItemTipo.valueOf(createDto.getEstadoItemTipo()));
        item.setItemNegociadoTipo(ItemNegociadoTipo.valueOf(createDto.getItemNegociadoTipo()));

        item.setObservacao(createDto.getObservacao());

        if (createDto.getEndereco() != null) {
            Endereco endereco = getEndereco(createDto);
            item.setEndereco(endereco);
        }

        if (createDto.getVeiculo() != null) {
            Veiculo veiculo = getVeiculo(createDto);
            item.setVeiculo(veiculo);
        }

        return item;
    }

    private static Endereco getEndereco(ItemCreateDto createDto) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(createDto.getEndereco().getLogradouro());
        endereco.setComplemento(createDto.getEndereco().getComplemento());
        endereco.setNumero(createDto.getEndereco().getNumero());
        endereco.setBairro(createDto.getEndereco().getBairro());
        endereco.setCidade(createDto.getEndereco().getCidade());
        endereco.setEstado(createDto.getEndereco().getEstado());
        endereco.setCep(createDto.getEndereco().getCep());
        return endereco;
    }

    private static Veiculo getVeiculo(ItemCreateDto createDto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(createDto.getVeiculo().getMarca());
        veiculo.setModelo(createDto.getVeiculo().getModelo());
        veiculo.setAnoFabricacao(createDto.getVeiculo().getAnoFabricacao());
        veiculo.setKilometragem(createDto.getVeiculo().getKilometragem());
        veiculo.setCor(createDto.getVeiculo().getCor());
        veiculo.setTipoCombustivel(createDto.getVeiculo().getTipoCombustivel());
        veiculo.setNumPortas(createDto.getVeiculo().getNumPortas());

        if (createDto.getVeiculo().getVeiculoCategoriaId() != null) {
            VeiculoCategoria veiculoCategoria = new VeiculoCategoria();
            veiculoCategoria.setId(createDto.getVeiculo().getVeiculoCategoriaId());
            veiculo.setVeiculoCategoria(veiculoCategoria);
        }
        return veiculo;
    }

    public static ItemResponseDto toDto(Item item) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Item.class, ItemResponseDto.class)
                .addMapping(Item::getVeiculo, ItemResponseDto::setVeiculo)
                .addMapping(Item::getEndereco, ItemResponseDto::setEndereco);

        return modelMapper.map(item, ItemResponseDto.class);
    }
}
