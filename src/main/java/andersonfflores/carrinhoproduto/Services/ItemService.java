package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.DTOmappers.ItemDTOMapper;
import andersonfflores.carrinhoproduto.Models.DTOs.ItemDTO;
import andersonfflores.carrinhoproduto.Models.Item;
import andersonfflores.carrinhoproduto.Repositories.CarrinhoRepository;
import andersonfflores.carrinhoproduto.Repositories.ItemRepository;
import andersonfflores.carrinhoproduto.Repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemDTOMapper itemDTOMapper;
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public ItemService(ItemRepository itemRepository, ItemDTOMapper itemDTOMapper,
                       CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository) {
        this.itemRepository = itemRepository;
        this.itemDTOMapper = itemDTOMapper;
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = (List<Item>) itemRepository.findAll();
        return items.stream()
                .map(itemDTOMapper)
                .collect(Collectors.toList());
    }

    public ItemDTO getItemById(UUID id) {
        return itemRepository.findById(id).map(itemDTOMapper)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public ItemDTO createItem(ItemDTO itemDTO) {
        itemRepository.save(new Item(
                itemDTO.itemId(),
                carrinhoRepository.findById(UUID.fromString(itemDTO.carrinhoId())).orElseThrow(
                        () -> new RuntimeException("Carrinho not found")),
                produtoRepository.findById(UUID.fromString(itemDTO.produtoId())).orElseThrow(
                        () -> new RuntimeException("produto not found")),
                itemDTO.quantidade()
        ));
        return itemDTO;
    }

    public ItemDTO updateItem(UUID id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setCarrinho(carrinhoRepository.findById(UUID.fromString(itemDTO.carrinhoId())).orElseThrow(
                () -> new RuntimeException("Carrinho not found")));
        item.setProduto(produtoRepository.findById(UUID.fromString(itemDTO.produtoId())).orElseThrow(
                () -> new RuntimeException("produto not found")));
        item.setQuantidade(itemDTO.quantidade());
        itemRepository.save(item);

        return itemDTO;
    }

    public void deleteItem(UUID id) {
        itemRepository.deleteById(id);
    }
}
