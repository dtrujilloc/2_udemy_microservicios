package edu.microservices.msitems.negocio.controlador;

import edu.microservices.msitems.comun.dto.ItemDto;
import edu.microservices.msitems.negocio.servicio.interfaz.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Clase que representa el controlador por el cual se exponen los endpoints para acceder a la API.
 * @author dtrujilloc
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    /**
     * Aqui podemos ver como estamos inyectando el servicio (la interface IItemService), pero existen dos implementaciones de esta interface.
     * Entonces la etiqueta @Qualifier nos permite espicificar el nombre de la implementacion que deseo inyectar. Este nombre tiene
     * que concordar con el nombre que se indico en la etiqueta @Service en la implementacion. Recordar que si no se especifico ningun nombre
     * en la etiqueta @Service, por defecto el nombre del Bean se crea con el mismo nombre de la clase pero inicianco con minuscula.
     *
     * Tambien recordar que en el caso que no se especifique la etiqueta @Qualifier, la implementacion que se inyectara, sera la que tenga
     * la etiqueta de @Primary.
     */
    @Autowired
    @Qualifier("itemRestTemplateService")
    private IItemService itemRestTemplateService;

    /**
     * Aqui vemos como no especificamos la etiqueta @Qualifier y aun asi no se genera ningun error. Esto es por que Tenemos una implementacion
     * que tiene la etiqueta @Primary, la cual concuerda con la implementacion que pretendemos inyectar en este caso. Si quisiera especificar la
     * inyeccion de dependencias, tendria que hacerlo asi @Qualifier("<nombre_de_la_implementacion>"), donde recordemos que el nombre de la
     * implementacion corresponde al nombre especificado dentro de la etiqueta @Service. En este caso como no especificamos el nombre, entonces
     * por defecto es el mismo nombre de la clase pero empezando con minuscula, es decir <nombre_de_la_implementacion>=itemFeignServiceImpl.
     *
     * La forma correcta de utilizar la etiqueta en este caso seria -> @Qualifier("itemFeignServiceImpl")
     */
    @Autowired
    private IItemService itemFeignService;

    @GetMapping("/rest-template")
    public List<ItemDto> obtenerItemTodosRestTemplate() throws Exception {
        log.info(">>> Start endpoint obtenerProductoTodos con RestTemplate");
        List<ItemDto> itemDtoList = itemRestTemplateService.obtenerTodos();
        log.info("<<< End endpoint obtenerProductoTodos con RestTemplate");
        return itemDtoList;
    }

    @GetMapping("/rest-template/{id}/{cantidad}")
    public ItemDto obtenerItemPorIdPoductoYCantidadRestTemplate(@PathVariable Long id, @PathVariable Integer cantidad) throws Exception {
        log.info(">>> Start endpoint obtenerItemPorIdPoductoYCantidad con RestTemplate");
        ItemDto itemDto = itemRestTemplateService.obtenerItemPorIdPoductoYCantidad(id, cantidad);
        log.info(">>> End endpoint obtenerItemPorIdPoductoYCantidad con RestTemplate");
        return itemDto;
    }

    @GetMapping("/feign")
    public List<ItemDto> obtenerItemTodosFeign() throws Exception {
        log.info(">>> Start endpoint obtenerProductoTodos con Feign");
        List<ItemDto> itemDtoList = itemFeignService.obtenerTodos();
        log.info("<<< End endpoint obtenerProductoTodos con Feign");
        return itemDtoList;
    }

    @GetMapping("/feign/{id}/{cantidad}")
    public ItemDto obtenerItemPorIdPoductoYCantidadFeign(@PathVariable Long id, @PathVariable Integer cantidad) throws Exception {
        log.info(">>> Start endpoint obtenerItemPorIdPoductoYCantidad con Feign");
        ItemDto itemDto = itemFeignService.obtenerItemPorIdPoductoYCantidad(id, cantidad);
        log.info(">>> End endpoint obtenerItemPorIdPoductoYCantidad con Feign");
        return itemDto;
    }
}
