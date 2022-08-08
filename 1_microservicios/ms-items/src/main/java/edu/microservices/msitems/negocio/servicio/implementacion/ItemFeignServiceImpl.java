package edu.microservices.msitems.negocio.servicio.implementacion;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.microservices.msitems.comun.dto.ItemDto;
import edu.microservices.msitems.comun.dto.ProductoDto;
import edu.microservices.msitems.configuracion.clienteshttp.ProductoFeignClient;
import edu.microservices.msitems.negocio.servicio.interfaz.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que implementa la interface de IItemService, es decir, va a tener el mismo comportamiento, pero la forma de conectarse
 * con el otro microservicio es por medio del cliente http de Feign.
 * @author dtrujilloc
 * @version 1.0
 *
 * Nota:
 *      - Podemos ver que podemos tener multiples clases que implementan la misma interface, como en el caso de las clases
 *      ItemFeignServiceImpl e ItemRestTemplateServiceImpl, por lo tanto cuando queramos inyectar estas clases por medio
 *      de la interface IItemService, spring-boot no sabria cual de las implementaciones tener en cuenta para inyectar. Es
 *      por esta razon que se especifica el nombre del Bean de la clase Service dentro de la etiqueta @Service para poder
 *      ser especificado en la inyeccion de dependencia por medio de la etiqueta @Qualifier.
 *
 *      - En la etiqueta @Service podemos especificar el nombre con el cual se va a crear el Bean de la clase eitiquetada.
 *      Si no se especifica el nombre, por defecto es el mismo nombre de la clase pero iniciando con minuscula.
 *
 *      - La etiqueta Primary sirve para especificar cual es la implementacion por defecto que se inyecta en caso tal de que
 *      existan dos o mas clases que implementen la misma interface y no se utilice la etiqueta @Qualifier para especificar
 *      la inyeccion de dependencias.
 *
 *      Ver ejemplo de lo hablado anteriormente en el controlador ItemController.
 */
@Slf4j
@Service
@Primary
public class ItemFeignServiceImpl implements IItemService {

    /**
     * Se inyecta la interface creada y configurada como cliente http con feign.
     */
    @Autowired
    private ProductoFeignClient productoFeignClient;

    @Override
    public List<ItemDto> obtenerTodos() throws Exception {
        log.info(">>> Start method obtenerTodos con Feign");

        log.info("---> Start conexion con ms-productos por medio de Feign como cliente http");
        List<ProductoDto> productoDtoList = productoFeignClient.obtenerProductoTodos();
        log.info("<--- end conexion con ms-productos por medio de Feign como cliente http");

        List<ItemDto> itemDtoList = productoDtoList.stream().map(productoDto -> new ItemDto(productoDto, 1)).collect(Collectors.toList());
        log.info("<<< End method obtenerTodos con Feign -> itemDtoListSize:{}", itemDtoList.size());
        return itemDtoList;
    }

    @HystrixCommand(fallbackMethod = "obtenerItemPorIdProductoYCantidadFallback")
    @Override
    public ItemDto obtenerItemPorIdProductoYCantidad(Long id, Integer cantidad) throws Exception {
        log.info(">>> Start method obtenerItemPorIdPoductoYCantidad con Feign -> productId:{}", id);

        log.info("---> Start conexion con ms-productos por medio de Feign como cliente http");
        ProductoDto productoDto = productoFeignClient.obtenerProductoPorId(id);
        log.info("<--- end conexion con ms-productos por medio de Feign como cliente http");

        ItemDto itemDto = new ItemDto(productoDto, cantidad);
        log.info("<<< End method obtenerItemPorIdPoductoYCantidad con Feign -> itemDto:{}", itemDto);
        return itemDto;
    }

    public ItemDto obtenerItemPorIdProductoYCantidadFallback(Long id, Integer cantidad) throws Exception {
        log.info(">>> Start fallback method obtenerItemPorIdProductoYCantidadFallback -> productId:{}", id);

        ProductoDto productoDto = ProductoDto.builder()
                .id(-1L)
                .nombre("producto fallback")
                .precio(0.0)
                .fechaCreacion(Calendar.getInstance().getTime())
                .build();

        ItemDto itemDto = new ItemDto(productoDto, cantidad);
        log.info("<<< End fallback method obtenerItemPorIdProductoYCantidadFallback -> itemDto:{}", itemDto);
        return itemDto;
    }
}
