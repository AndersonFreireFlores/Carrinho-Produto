package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.DTOmappers.CarrinhoDTOMapper;
import andersonfflores.carrinhoproduto.Models.DTOs.CarrinhoDTO;
import andersonfflores.carrinhoproduto.Models.Item;
import andersonfflores.carrinhoproduto.Repositories.CarrinhoRepository;
import andersonfflores.carrinhoproduto.Repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CarrinhoService {

    private  final CarrinhoRepository carrinhoRepository;
    private final CarrinhoDTOMapper carrinhoDTOMapper;
    private final ItemRepository itemRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, CarrinhoDTOMapper carrinhoDTOMapper, ItemRepository itemRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.carrinhoDTOMapper = carrinhoDTOMapper;
        this.itemRepository = itemRepository;
    }

    public List<CarrinhoDTO> getCarrinhos() {
        List<Carrinho> carrinhos = (List<Carrinho>) carrinhoRepository.findAll();

        List<CarrinhoDTO> carrinhoDTOS =  carrinhos
                .stream()
                .map(carrinhoDTOMapper)
                .toList();
        return carrinhoDTOS;
    }

    public CarrinhoDTO getCarrinhoById(UUID id) {
        return carrinhoRepository.findById(id)
                .map(carrinhoDTOMapper)
                .orElseThrow(
                        () -> new RuntimeException("Carrinho not found"));
    }

    public CarrinhoDTO save(CarrinhoDTO carrinhoDTO) {
        carrinhoRepository.save(
                new Carrinho(
                        carrinhoDTO.id(),
                        calcularValorCarrinho(carrinhoDTO.id()),
                        itemRepository.findAllItemsbyCarrinho(carrinhoDTO.id())
                ));
        return carrinhoDTO;
    }


    public void update(UUID id) {
        Carrinho carrinho = carrinhoRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Carrinho not found"));
        carrinho.setPreco_total(calcularValorCarrinho(id));
        carrinho.setItens(itemRepository.findAllItemsbyCarrinho(id));
        carrinhoRepository.save(carrinho);
    }

    public void delete(UUID id) {
        carrinhoRepository.deleteById(id);
    }


    public BigDecimal calcularValorCarrinho(UUID id) {
        List<BigDecimal> list = carrinhoRepository.calcularValorCarrinho(id);
        return list.stream().reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }




}
