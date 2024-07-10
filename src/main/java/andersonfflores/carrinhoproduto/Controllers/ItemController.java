package andersonfflores.carrinhoproduto.Controllers;

import andersonfflores.carrinhoproduto.Models.DTOs.ItemDTO;
import andersonfflores.carrinhoproduto.Services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<ItemDTO> getItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/item/{id}")
    public ItemDTO getItemById(@PathVariable UUID id) {
        return itemService.getItemById(id);
    }

    @PostMapping("/item")
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO) {
        return  itemService.createItem(itemDTO);
    }

    @PutMapping("/item/{id}")
    public ItemDTO updateItem(@PathVariable UUID id, @RequestBody ItemDTO itemDTO) {
        return itemService.updateItem(id, itemDTO);
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable UUID id) {
        itemService.deleteItem(id);
    }


}
