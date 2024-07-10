package andersonfflores.carrinhoproduto.Models.DTOmappers;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.DTOs.CarrinhoDTO;
import andersonfflores.carrinhoproduto.Models.DTOs.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class CarrinhoDTOMapper implements Function<Carrinho, CarrinhoDTO> {

    private final ItemDTOMapper itemDTOMapper;

    public CarrinhoDTOMapper(ItemDTOMapper itemDTOMapper) {
        this.itemDTOMapper = itemDTOMapper;
    }

    @Override
    public CarrinhoDTO apply(Carrinho carrinho) {
        return new CarrinhoDTO(
                carrinho.getId(),
                carrinho.getPreco_total(),
                carrinho.getItens().stream()
                        .map(itemDTOMapper)
                        .toList()
        );
    }


}
