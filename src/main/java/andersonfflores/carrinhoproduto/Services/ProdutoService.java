package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.DTOs.ProdutoDTO;
import andersonfflores.carrinhoproduto.Models.Produto;
import andersonfflores.carrinhoproduto.Repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> findProdutos() {
        return StreamSupport.stream(produtoRepository.findAll().spliterator(), false)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO findById(UUID id) {
        return produtoRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Produto produto = fromDTO(produtoDTO);
        produtoRepository.save(produto);
        return toDTO(produto);
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produtoDTO) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoDTO.nome());
                    produto.setDescricao(produtoDTO.descricao());
                    produto.setPrecoUnitario(produtoDTO.precoUnitario());
                    produtoRepository.save(produto);
                    return toDTO(produto);
                }).orElse(null);
    }

    public void delete(UUID id) {
        produtoRepository.deleteById(id);
    }

    public Produto fromDTO(ProdutoDTO produtoDTO) {
        return new Produto(produtoDTO.nome(), produtoDTO.descricao(), produtoDTO.precoUnitario());
    }

    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPrecoUnitario());
    }
}