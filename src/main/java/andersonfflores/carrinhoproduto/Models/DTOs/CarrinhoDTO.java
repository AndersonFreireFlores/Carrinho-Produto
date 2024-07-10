package andersonfflores.carrinhoproduto.Models.DTOs;

import jakarta.persistence.GeneratedValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CarrinhoDTO(@GeneratedValue UUID id, @GeneratedValue BigDecimal preco_total,  List<ItemDTO> items ) {
}
