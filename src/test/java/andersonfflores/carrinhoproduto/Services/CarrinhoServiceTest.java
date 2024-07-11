package andersonfflores.carrinhoproduto.Services;

import andersonfflores.carrinhoproduto.Models.DTOmappers.CarrinhoDTOMapper;
import andersonfflores.carrinhoproduto.Repositories.CarrinhoRepository;
import andersonfflores.carrinhoproduto.Repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CarrinhoServiceTest {

    @Mock
    private   CarrinhoRepository carrinhoRepository;
    @Autowired
    private  CarrinhoDTOMapper carrinhoDTOMapper;
    @Mock
    private  ItemRepository itemRepository;

    private  CarrinhoService service;

    @BeforeEach
    void setUp() {

        service = new CarrinhoService(carrinhoRepository,carrinhoDTOMapper,itemRepository);
    }

    @Test
    void getCarrinhos() {
        service.getCarrinhos();
        verify(carrinhoRepository).findAll();
    }

    @Test
    void getCarrinhoById() {

    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

}