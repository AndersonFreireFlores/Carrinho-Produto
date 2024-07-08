package andersonfflores.carrinhoproduto.Models.DTOs;


import andersonfflores.carrinhoproduto.Models.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CarrinhoDTO(UUID id, BigDecimal preco_total, @JsonIgnore List<Item> items ) {
}
