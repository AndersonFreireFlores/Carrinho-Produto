package andersonfflores.carrinhoproduto.Models.DTOs;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public record ItemDTO( @GeneratedValue UUID itemId, String carrinhoId, String produtoId, int quantidade) {

}