package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.DTOs.ProdutoDTO;
import andersonfflores.carrinhoproduto.Models.Produto;
import andersonfflores.carrinhoproduto.Repositories.ProdutoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void findProdutos() {
        when(produtoRepository.findAll()).thenReturn(Collections.singletonList(new Produto("Teste", "Descricao", BigDecimal.TEN)));
        assertFalse(produtoService.findProdutos().isEmpty());
        verify(produtoRepository).findAll();
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto("Teste", "Descricao", BigDecimal.TEN);
        produto.setId(id);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        assertNotNull(produtoService.findById(id));
        verify(produtoRepository).findById(id);
    }

    @Test
    void save() {
        ProdutoDTO produtoDTO = new ProdutoDTO(UUID.randomUUID(), "Teste", "Descricao", BigDecimal.TEN);
        Produto produto = new Produto("Teste", "Descricao", BigDecimal.TEN);
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        ProdutoDTO savedProdutoDTO = produtoService.save(produtoDTO);
        assertEquals(produtoDTO.nome(), savedProdutoDTO.nome());
    }

    @Test
    void update() {
    UUID id = UUID.randomUUID();
    ProdutoDTO produtoDTO = new ProdutoDTO(id, "Updated Teste", "Updated Descricao", BigDecimal.valueOf(20));
    Produto produto = new Produto("Teste", "Descricao", BigDecimal.TEN);
    produto.setId(id);

    when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
    when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

    ProdutoDTO updatedProdutoDTO = produtoService.update(id, produtoDTO);

    assertNotNull(updatedProdutoDTO);
    assertEquals(produtoDTO.nome(), updatedProdutoDTO.nome());
    assertEquals(produtoDTO.descricao(), updatedProdutoDTO.descricao());
    assertEquals(produtoDTO.precoUnitario(), updatedProdutoDTO.precoUnitario());
    verify(produtoRepository).findById(id);
    verify(produtoRepository).save(produto);
    }

    @Test
    void delete() {
        UUID id = UUID.randomUUID();
        doNothing().when(produtoRepository).deleteById(id);

        produtoService.delete(id);

        verify(produtoRepository).deleteById(id);
    }

    @Test
    void fromDTO() {
        ProdutoDTO produtoDTO = new ProdutoDTO(UUID.randomUUID(), "Teste", "Descricao", BigDecimal.TEN);
        Produto produto = produtoService.fromDTO(produtoDTO);

        assertNotNull(produto);
        assertEquals(produtoDTO.nome(), produto.getNome());
        assertEquals(produtoDTO.descricao(), produto.getDescricao());
        assertEquals(produtoDTO.precoUnitario(), produto.getPrecoUnitario());
    }

    @Test
    void toDTO() {
        Produto produto = new Produto("Teste", "Descricao", BigDecimal.TEN);
        produto.setId(UUID.randomUUID());
        ProdutoDTO produtoDTO = produtoService.toDTO(produto);

        assertNotNull(produtoDTO);
        assertEquals(produto.getId(), produtoDTO.id());
        assertEquals(produto.getNome(), produtoDTO.nome());
        assertEquals(produto.getDescricao(), produtoDTO.descricao());
        assertEquals(produto.getPrecoUnitario(), produtoDTO.precoUnitario());
    }
    }