package andersonfflores.carrinhoproduto.Models.DTOs;


import andersonfflores.carrinhoproduto.Models.Item;

import java.math.BigDecimal;
import java.util.List;

public record CarrinhoDTO(BigDecimal preco, List<Item> items ) {
}
