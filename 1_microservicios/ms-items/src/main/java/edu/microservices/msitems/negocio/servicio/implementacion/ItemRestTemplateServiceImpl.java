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

/**
 * Clase que implementa la interface de IItemService, es decir, va a tener el mismo comportamiento, pero la forma de conectarse
 * con el otro microservicio es por medio del cliente http de RestTemplate.
 * @author dtrujilloc
 * @version 1.0
 */
@Slf4j
@Service("itemRestTemplateService")
public class ItemRestTemplateServiceImpl implements IItemService {

    /**
     * Se inyecta el Bean creado y configurado para restTemplate en la clase de RestTemplateConfig
     */
    @Autowired
    private RestTemplate restTemplateClient;

    @Override
    public List<ItemDto> obtenerTodos() throws Exception {
        log.info(">>> Start method obtenerTodos con RestTemplate");

        log.info("---> Start conexion con ms-productos por medio de RestTemplate como cliente http");
        ProductoDto[] productoDtoArreglo = restTemplateClient.getForObject("http://localhost:8081/productos", ProductoDto[].class);
        log.info("<--- end conexion con ms-productos por medio de RestTemplate como cliente http");

        List<ProductoDto> productoDtoList = Arrays.asList(productoDtoArreglo);
        List<ItemDto> itemDtoList = productoDtoList.stream().map(productoDto -> new ItemDto(productoDto, 1)).collect(Collectors.toList());
        log.info("<<< End method obtenerTodos con RestTemplate-> itemDtoListSize:{}", itemDtoList.size());
        return itemDtoList;
    }

    @Override
    public ItemDto obtenerItemPorIdProductoYCantidad(Long id, Integer cantidad) throws Exception {
        log.info(">>> Start method obtenerItemPorIdPoductoYCantidad  con RestTemplate -> productId:{}", id);

        log.info("---> Start conexion con ms-productos por medio de RestTemplate como cliente http");
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        ProductoDto productoDto = restTemplateClient.getForObject("http://localhost:8081/productos/{id}", ProductoDto.class, pathVariables);
        log.info("<--- end conexion con ms-productos por medio de RestTemplate como cliente http");

        ItemDto itemDto = new ItemDto(productoDto, cantidad);
        log.info("<<< End method obtenerItemPorIdPoductoYCantidad con RestTemplate -> itemDto:{}", itemDto);
        return itemDto;
    }
}
