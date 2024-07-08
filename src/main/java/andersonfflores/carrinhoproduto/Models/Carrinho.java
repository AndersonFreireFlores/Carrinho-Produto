package andersonfflores.carrinhoproduto.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carrinho")
public class Carrinho {

    @Id
    private UUID id;

    private BigDecimal preco;

    @OneToMany
    private List<Item> itens;

    public Carrinho() {
    }

    public Carrinho(UUID id, BigDecimal preco, List<Item> itens) {
        this.id = id;
        this.preco = preco;
        this.itens = itens;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
