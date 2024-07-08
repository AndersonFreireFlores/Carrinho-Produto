package andersonfflores.carrinhoproduto.Models.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemDTO(UUID id, UUID carrinhoId, UUID produtoId, int quantidade, BigDecimal valorTotal) {
}
