package andersonfflores.carrinhoproduto.Models.DTOs;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.Produto;

import java.util.UUID;

public record ItemDTO(UUID id, Carrinho carrinho, Produto produto, int quantidade) {
}
