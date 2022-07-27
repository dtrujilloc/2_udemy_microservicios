package edu.microservices.msitems.negocio.controlador;

import edu.microservices.msitems.comun.dto.ItemDto;
import edu.microservices.msitems.negocio.servicio.interfaz.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private IItemService iItemService;

    @GetMapping
    public List<ItemDto> obtenerItemTodos() throws Exception {
        log.info(">>> Start endpoint obtenerProductoTodos");
        List<ItemDto> itemDtoList = iItemService.obtenerTodos();
        log.info("<<< End endpoint obtenerProductoTodos");
        return itemDtoList;
    }

    @GetMapping("/{id}/{cantidad}")
    public ItemDto obtenerItemPorIdPoductoYCantidad(@PathVariable Long id, @PathVariable Integer cantidad) throws Exception {
        log.info(">>> Start endpoint obtenerItemPorIdPoductoYCantidad");
        ItemDto itemDto = iItemService.obtenerItemPorIdPoductoYCantidad(id, cantidad);
        log.info(">>> End endpoint obtenerItemPorIdPoductoYCantidad");
        return itemDto;
    }
}
