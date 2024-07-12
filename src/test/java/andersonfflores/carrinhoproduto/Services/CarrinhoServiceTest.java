package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.DTOs.CarrinhoDTO;
import andersonfflores.carrinhoproduto.Models.DTOs.ItemDTO;
import andersonfflores.carrinhoproduto.Repositories.CarrinhoRepository;
import andersonfflores.carrinhoproduto.Repositories.ItemRepository;
import andersonfflores.carrinhoproduto.Models.DTOmappers.CarrinhoDTOMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



public class CarrinhoServiceTest {

    @Mock
    private CarrinhoRepository carrinhoRepository;

    @Mock
    private CarrinhoDTOMapper carrinhoDTOMapper;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private CarrinhoService carrinhoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getCarrinhos() {
        // Setup
        List<Carrinho> carrinhos = Arrays.asList(new Carrinho());
        when(carrinhoRepository.findAll()).thenReturn(carrinhos);
        // Assuming you have a way to create or mock a UUID and a List<ItemDTO>
        UUID mockId = UUID.randomUUID();
        BigDecimal mockPrecoTotal = BigDecimal.ZERO; // or any relevant value
        List<ItemDTO> mockItems = Collections.emptyList(); // or any relevant list
        when(carrinhoDTOMapper.apply(any(Carrinho.class))).thenReturn(new CarrinhoDTO(mockId, mockPrecoTotal, mockItems));

        // Execute
        List<CarrinhoDTO> result = carrinhoService.getCarrinhos();

        // Verify
        assertEquals(1, result.size());
        verify(carrinhoRepository).findAll();
    }

    @Test
    void getCarrinhoById() {
        // Setup
        UUID id = UUID.randomUUID();
        Carrinho carrinho = new Carrinho();
        when(carrinhoRepository.findById(id)).thenReturn(Optional.of(carrinho));
        BigDecimal mockPrecoTotal = BigDecimal.ZERO; // or any relevant value
        List<ItemDTO> mockItems = Collections.emptyList(); // or any relevant list
        when(carrinhoDTOMapper.apply(carrinho)).thenReturn(new CarrinhoDTO(id, mockPrecoTotal, mockItems));

        // Execute
        CarrinhoDTO result = carrinhoService.getCarrinhoById(id);

        // Verify
        assertNotNull(result);
        verify(carrinhoRepository).findById(id);
    }

    @Test
    void save() {
        // Setup
        UUID mockId = UUID.randomUUID();
        BigDecimal mockPrecoTotal = BigDecimal.ZERO; // or any relevant value
        List<ItemDTO> mockItems = Collections.emptyList(); // or any relevant list
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO(mockId, mockPrecoTotal, mockItems);
        when(carrinhoRepository.save(any(Carrinho.class))).thenAnswer(i -> i.getArguments()[0]);

        // Execute
        CarrinhoDTO result = carrinhoService.save(carrinhoDTO);

        // Verify
        assertEquals(carrinhoDTO, result);
        verify(carrinhoRepository).save(any(Carrinho.class));
    }

    @Test
    void update() {
        // Setup
        UUID id = UUID.randomUUID();
        Carrinho carrinho = new Carrinho();
        when(carrinhoRepository.findById(id)).thenReturn(Optional.of(carrinho));

        // Execute
        carrinhoService.update(id);

        // Verify
        verify(carrinhoRepository).save(carrinho);
    }

    @Test
    void delete() {
        // Setup
        UUID id = UUID.randomUUID();

        // Execute
        carrinhoService.delete(id);

        // Verify
        verify(carrinhoRepository).deleteById(id);
    }

    @Test
    void calcularValorCarrinho() {
        // Setup
        UUID id = UUID.randomUUID();
        List<BigDecimal> valores = Arrays.asList(BigDecimal.TEN, BigDecimal.ONE);
        when(carrinhoRepository.calcularValorCarrinho(id)).thenReturn(valores);

        // Execute
        BigDecimal result = carrinhoService.calcularValorCarrinho(id);

        // Verify
        assertEquals(new BigDecimal("11"), result);
        verify(carrinhoRepository).calcularValorCarrinho(id);
    }
}