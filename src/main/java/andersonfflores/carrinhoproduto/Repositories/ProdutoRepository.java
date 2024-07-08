package andersonfflores.carrinhoproduto.Repositories;

import andersonfflores.carrinhoproduto.Models.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, UUID> {
}
