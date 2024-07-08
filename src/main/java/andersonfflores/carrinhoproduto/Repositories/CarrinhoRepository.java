package andersonfflores.carrinhoproduto.Repositories;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarrinhoRepository extends CrudRepository<Carrinho, UUID> {
}
