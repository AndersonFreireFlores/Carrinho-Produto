package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.Carrinho;
import andersonfflores.carrinhoproduto.Models.DTOs.CarrinhoDTO;
import andersonfflores.carrinhoproduto.Repositories.CarrinhoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarrinhoService {

    private  final CarrinhoRepository carrinhoRepository;


    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public List<CarrinhoDTO> getCarrinhos() {
        Iterable<Carrinho> carrinhos = carrinhoRepository.findAll();
        List<CarrinhoDTO> carrinhoDTOs = new ArrayList<>();
        carrinhos.forEach(carrinho -> carrinhoDTOs.add(toDTO(carrinho)));
        return carrinhoDTOs;
    }

    public CarrinhoDTO getCarrinhoById(UUID id) {
        Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
        if (carrinho.isPresent()) {
            return toDTO(carrinho.get());
        }
        return null;
    }

    public CarrinhoDTO save(CarrinhoDTO carrinhoDTO) {
        carrinhoRepository.save(fromDTO(carrinhoDTO));
        return carrinhoDTO;
    }


    public CarrinhoDTO update(UUID id,CarrinhoDTO carrinhoDTO) {
        Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
        if (carrinho.isPresent()) {
            carrinho.get().setPreco_total(carrinhoDTO.preco_total());
            carrinho.get().setItens(carrinhoDTO.items());
            carrinhoRepository.save(carrinho.get());
            return toDTO(carrinho.get());
        }
        return null;
    }

    public void delete(UUID id) {
        carrinhoRepository.deleteById(id);
    }

    public Carrinho fromDTO(CarrinhoDTO carrinhoDTO) {
        return new Carrinho(carrinhoDTO.preco_total(),carrinhoDTO.items());
    }

    public CarrinhoDTO toDTO(Carrinho carrinho) {
        return new CarrinhoDTO(carrinho.getId(),carrinho.getPreco_total(),carrinho.getItens());
    }


}
