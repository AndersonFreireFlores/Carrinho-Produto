package andersonfflores.carrinhoproduto.Repositories;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.Item;
import andersonfflores.carrinhoproduto.Models.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void findAllItemsbyCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinhoRepository.save(carrinho);
        Produto produto = new Produto();
        produtoRepository.save(produto);
        Item item = new Item(
                UUID.randomUUID(),
                carrinho,
                produto,
                1
        );

        itemRepository.save(item);
        List<Item> itemList = itemRepository.findAllItemsbyCarrinho(carrinho.getId());
        assertThat(itemList.size()).isEqualTo(1);
    }
}