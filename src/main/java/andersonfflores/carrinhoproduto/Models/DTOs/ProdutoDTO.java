package andersonfflores.carrinhoproduto.Models.DTOs;

import java.math.BigDecimal;

public record ProdutoDTO(String nome, String descricao, BigDecimal precoUnitario) {
}
