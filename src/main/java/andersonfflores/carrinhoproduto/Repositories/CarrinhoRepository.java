package andersonfflores.carrinhoproduto.Repositories;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import java.util.UUID;

@Repository
public interface CarrinhoRepository extends CrudRepository<Carrinho, UUID> {

    @Query(value = "SELECT (produto.precounitario * item.quantidade) from item join produto on item.produto_id=produto.id where item.carrinho_id = :id",
            nativeQuery = true)
    List<BigDecimal> calcularValorCarrinho(@Param("id") UUID id);
}
