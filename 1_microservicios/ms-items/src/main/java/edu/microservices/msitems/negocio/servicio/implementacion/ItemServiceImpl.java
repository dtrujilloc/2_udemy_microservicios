package edu.microservices.msitems.negocio.servicio.implementacion;

import edu.microservices.msitems.comun.dto.ItemDto;
import edu.microservices.msitems.comun.dto.ProductoDto;
import edu.microservices.msitems.negocio.servicio.interfaz.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements IItemService {


    @Override
    public List<ItemDto> obtenerTodos() throws Exception {
        log.info(">>> Start method obtenerTodos");
        throw new Exception("Falta la implementacino");
//        log.info("<<< End method obtenerTodos -> productoDtoListSize:{}", productoDtoList.size());
//        return productoDtoList;
    }

    @Override
    public ItemDto obtenerItemPorIdPoductoYCantidad(Long id, Integer cantidad) throws Exception {
        log.info(">>> Start method obtenerItemPorIdPoductoYCantidad -> productId:{}", id);
        throw new Exception("Falta la implementacino");
//        log.info("<<< End method obtenerItemPorIdPoductoYCantidad -> producto:{}", productoDto);
//        return productoDto;
    }
}
