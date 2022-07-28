package edu.microservices.msitems.negocio.servicio.implementacion;

import edu.microservices.msitems.comun.dto.ItemDto;
import edu.microservices.msitems.comun.dto.ProductoDto;
import edu.microservices.msitems.negocio.servicio.interfaz.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemServiceImpl implements IItemService {

    /**
     * Se inyecta el Bean creado y configurado para restTemplate en la clase de RestTemplateConfig
     */
    @Autowired
    private RestTemplate restTemplateClient;

    @Override
    public List<ItemDto> obtenerTodos() throws Exception {
        log.info(">>> Start method obtenerTodos");

        log.info("---> Start conexion con ms-productos");
        ProductoDto[] productoDtoArreglo = restTemplateClient.getForObject("http://localhost:8081/productos", ProductoDto[].class);
        log.info("<--- end conexion con ms-productos");

        List<ProductoDto> productoDtoList = Arrays.asList(productoDtoArreglo);
        List<ItemDto> itemDtoList = productoDtoList.stream().map(productoDto -> new ItemDto(productoDto, 1)).collect(Collectors.toList());
        log.info("<<< End method obtenerTodos -> itemDtoListSize:{}", itemDtoList.size());
        return itemDtoList;
    }

    @Override
    public ItemDto obtenerItemPorIdPoductoYCantidad(Long id, Integer cantidad) throws Exception {
        log.info(">>> Start method obtenerItemPorIdPoductoYCantidad -> productId:{}", id);

        log.info("---> Start conexion con ms-productos");
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        ProductoDto productoDto = restTemplateClient.getForObject("http://localhost:8081/productos/{id}", ProductoDto.class, pathVariables);
        log.info("<--- end conexion con ms-productos");

        ItemDto itemDto = new ItemDto(productoDto, cantidad);
        log.info("<<< End method obtenerItemPorIdPoductoYCantidad -> itemDto:{}", itemDto);
        return itemDto;
    }
}
