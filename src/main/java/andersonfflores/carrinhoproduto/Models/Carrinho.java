package andersonfflores.carrinhoproduto.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carrinho")
public class Carrinho {

    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal preco_total;

    @OneToMany
    @JsonIgnore
    private List<Item> itens;

    public Carrinho() {
    }

    public Carrinho(BigDecimal preco_total, List<Item> itens) {
        this.preco_total = preco_total;
        this.itens = itens;
    }

    public Carrinho(UUID id, BigDecimal preco_total, List<Item> itens) {
        this.id = id;
        this.preco_total = preco_total;
        this.itens = itens;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(BigDecimal preco_total) {
        this.preco_total = preco_total;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void addItem(Item item) {
        itens.add(item);
    }


}
