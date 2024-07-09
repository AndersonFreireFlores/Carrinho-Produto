package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.DTOs.ItemDTO;
import andersonfflores.carrinhoproduto.Models.Item;
import andersonfflores.carrinhoproduto.Models.Produto;
import andersonfflores.carrinhoproduto.Repositories.CarrinhoRepository;
import andersonfflores.carrinhoproduto.Repositories.ItemRepository;
import andersonfflores.carrinhoproduto.Repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ItemService {

    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDTO> getAllItems() {
        Iterable<Item> itemsIterable = itemRepository.findAll();
        List<ItemDTO> itemList = new ArrayList<>();
        itemsIterable.forEach(item -> itemList.add(toDTO(item)));
        return itemList;
    }

    public ItemDTO getItemById(UUID id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            return toDTO(itemOptional.get());
        }
        return null;
    }

    public ItemDTO saveItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.carrinho(),itemDTO.produto(),itemDTO.quantidade());
        itemRepository.save(item);

        return itemDTO;
    }

    public ItemDTO updateItem(UUID id, ItemDTO itemDTO) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            itemOptional.get().setCarrinho(itemDTO.carrinho());
            itemOptional.get().setProduto(itemDTO.produto());
            itemOptional.get().setQuantidade(itemDTO.quantidade());
            itemRepository.save(itemOptional.get());
        }
        return itemDTO;
    }

    public void deleteItem(UUID id) {
        itemRepository.deleteById(id);
    }

    public Item fromDTO(ItemDTO itemDTO) {
        return new Item(itemDTO.id(),
                itemDTO.carrinho()
                ,itemDTO.produto()
                ,itemDTO.quantidade());
    }

    public ItemDTO toDTO(Item item) {
        return new ItemDTO(item.getId(),
                item.getCarrinho(),
                item.getProduto(),
                item.getQuantidade());

    }
}
