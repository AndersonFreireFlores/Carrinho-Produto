package andersonfflores.carrinhoproduto.Models.DTOs;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoDTO(UUID id, String nome, String descricao, @Column(name = "precounitario") BigDecimal precoUnitario) {
}
