package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.DTOs.ProdutoDTO;
import andersonfflores.carrinhoproduto.Models.Produto;
import andersonfflores.carrinhoproduto.Repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> findProdutos() {
        Iterable<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        produtos.forEach(produto -> produtoDTOs.add(toDTO(produto)));
        return produtoDTOs;
    }

    public ProdutoDTO findById(UUID id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return toDTO(produto.get());
        }
        return null;
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        produtoRepository.save(fromDTO(produtoDTO));
        return produtoDTO;
    }

    public ProdutoDTO update(UUID id,ProdutoDTO produtoDTO) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produto.get().setNome(produtoDTO.nome());
            produto.get().setDescricao(produtoDTO.descricao());
            produto.get().setPrecoUnitario(produtoDTO.precoUnitario());
            produtoRepository.save(produto.get());
            return toDTO(produto.get());
        }
        return null;
    }

    public void delete(UUID id) {
        produtoRepository.deleteById(id);
    }

    public Produto fromDTO(ProdutoDTO produtoDTO) {
        return new Produto(produtoDTO.nome(),produtoDTO.descricao(),produtoDTO.precoUnitario());
    }

    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(),produto.getDescricao(),produto.getPrecoUnitario());
    }

}
