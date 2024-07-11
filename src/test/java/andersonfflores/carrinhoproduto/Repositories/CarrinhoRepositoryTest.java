package andersonfflores.carrinhoproduto.Repositories;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.DTOmappers.ItemDTOMapper;
import andersonfflores.carrinhoproduto.Models.Item;
import andersonfflores.carrinhoproduto.Models.Produto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CarrinhoRepositoryTest {


    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemRepository itemRepository;


    @Test
    void calcularValorCarrinho() {
        Carrinho carrinho = new Carrinho();
        Item item = new Item();
        Produto produto = new Produto();
        carrinho.setItens(new ArrayList<Item>());
        carrinho.getItens().add(item);
        item.setProduto(produto);
        item.setQuantidade(2);
        produto.setPrecoUnitario(BigDecimal.valueOf(10));
        itemRepository.save(item);
        produtoRepository.save(produto);
        carrinhoRepository.save(carrinho);

        List<BigDecimal> bigDecimalList = carrinhoRepository.calcularValorCarrinho(carrinho.getId());
        BigDecimal valorFinal = bigDecimalList.stream().reduce(BigDecimal.valueOf(0), BigDecimal::add);
        assertThat(valorFinal.equals(BigDecimal.valueOf(20)));
    }


}