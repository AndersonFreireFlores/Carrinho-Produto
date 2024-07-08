package andersonfflores.carrinhoproduto.Controllers;

import andersonfflores.carrinhoproduto.Models.DTOs.ProdutoDTO;
import andersonfflores.carrinhoproduto.Services.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProdutoController {


    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(value = "/produtos")
    public List<ProdutoDTO> getAllProdutos() {
        return produtoService.findProdutos();
    }

    @GetMapping(value = "/produto/{id}")
    public Optional<ProdutoDTO> getProdutoById(@PathVariable UUID id) {
        return Optional.ofNullable(produtoService.findById(id));
    }

    @PostMapping(value = "/produto")
    public ProdutoDTO postProduto(@RequestBody ProdutoDTO produto) {
        return produtoService.save(produto);
    }

    @PutMapping(value = "/produto/{id}")
    public ProdutoDTO putProduto(@PathVariable UUID id, @RequestBody ProdutoDTO produto) {
        return produtoService.update(id, produto);
    }

    @DeleteMapping(value = "/produto/{id}")
    public void deleteProduto(@PathVariable UUID id) {
        produtoService.delete(id);
    }

}
