package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.DTOmappers.ItemDTOMapper;
import andersonfflores.carrinhoproduto.Models.DTOs.ItemDTO;
import andersonfflores.carrinhoproduto.Models.Item;
import andersonfflores.carrinhoproduto.Repositories.CarrinhoRepository;
import andersonfflores.carrinhoproduto.Repositories.ItemRepository;
import andersonfflores.carrinhoproduto.Repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemDTOMapper itemDTOMapper;
    @Mock
    private CarrinhoRepository carrinhoRepository;
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllItems() {
        List<Item> items = List.of(mock(Item.class));
        when(itemRepository.findAll()).thenReturn(items);
        when(itemDTOMapper.apply(any(Item.class))).thenReturn(mock(ItemDTO.class));
        List<ItemDTO> result = itemService.getAllItems();
        verify(itemRepository).findAll();
        verify(itemDTOMapper, times(items.size())).apply(any(Item.class));
        assert !result.isEmpty();
    }

    @Test
    void getItemById() {
        UUID id = UUID.randomUUID();
        Item item = mock(Item.class);
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(itemDTOMapper.apply(item)).thenReturn(mock(ItemDTO.class));
        ItemDTO result = itemService.getItemById(id);
        verify(itemRepository).findById(id);
        verify(itemDTOMapper).apply(item);
        assert result != null;
    }

       @Test
    void createItem() {
        ItemDTO itemDTO = mock(ItemDTO.class);
        when(itemDTO.carrinhoId()).thenReturn(UUID.randomUUID().toString());
        when(itemDTO.produtoId()).thenReturn(UUID.randomUUID().toString());
        when(carrinhoRepository.findById(any(UUID.class))).thenReturn(mock(Optional.class));
        when(produtoRepository.findById(any(UUID.class))).thenReturn(mock(Optional.class));
        itemService.createItem(itemDTO);
        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void updateItem() {
        UUID id = UUID.randomUUID();
        ItemDTO itemDTO = new ItemDTO(UUID.randomUUID(),(UUID.randomUUID()).toString(), (UUID.randomUUID()).toString(), 1);
        Item item = mock(Item.class);
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(carrinhoRepository.findById(any(UUID.class))).thenReturn(mock(Optional.class));
        when(produtoRepository.findById(any(UUID.class))).thenReturn(mock(Optional.class));
        itemService.updateItem(id, itemDTO);
        verify(itemRepository).save(item);
        }
}