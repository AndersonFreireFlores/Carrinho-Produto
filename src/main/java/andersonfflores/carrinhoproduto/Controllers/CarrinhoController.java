package andersonfflores.carrinhoproduto.Controllers;

import andersonfflores.carrinhoproduto.Models.DTOs.CarrinhoDTO;
import andersonfflores.carrinhoproduto.Services.CarrinhoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping(value = "/carrinhos")
    public List<CarrinhoDTO> getCarrinhos() {
        return carrinhoService.getCarrinhos();
    }

    @GetMapping(value = "/carrinho/{id}")
    public Optional<CarrinhoDTO> getCarrinho(@PathVariable UUID id) {
        return Optional.ofNullable(carrinhoService.getCarrinhoById(id));
    }

    @PostMapping(value = "/carrinho")
    public CarrinhoDTO postCarrinho(@RequestBody CarrinhoDTO carrinhoDTO) {
        return carrinhoService.save(carrinhoDTO);
    }

    @PutMapping(value = "/carrinho/{id}")
    public CarrinhoDTO putCarrinho(@PathVariable UUID id, @RequestBody CarrinhoDTO carrinhoDTO) {
        return carrinhoService.update(id, carrinhoDTO);
    }

    @DeleteMapping(value = "/carrinho/{id}")
    public void deleteCarrinho(@PathVariable UUID id) {
        carrinhoService.delete(id);
    }

}
