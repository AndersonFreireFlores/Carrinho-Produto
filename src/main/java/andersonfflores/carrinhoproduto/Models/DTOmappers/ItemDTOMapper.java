package andersonfflores.carrinhoproduto.Models.DTOmappers;

import andersonfflores.carrinhoproduto.Models.DTOs.ItemDTO;
import andersonfflores.carrinhoproduto.Models.Item;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Function;

@Service
public class ItemDTOMapper implements Function<Item, ItemDTO> {

    @Override
    public ItemDTO apply(Item item) {
        return new ItemDTO(
                        item.getId(),
                        (item.getCarrinho().getId()).toString(),
                        (item.getProduto().getId()).toString(),
                        item.getQuantidade()
                );
    }
}
