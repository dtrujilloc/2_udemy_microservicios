package edu.microservices.msitems.negocio.servicio.interfaz;

import edu.microservices.msitems.comun.dto.ItemDto;

import java.util.List;

/**
 * Interface que representa el servicio de Item, es decir donde estara la logica de negocio relaiconado a item
 * @author dtrujilloc
 * @version 1.0
 */
public interface IItemService {

    /**
     * Permite obtener todos los registros de items
     * @return
     */
    List<ItemDto> obtenerTodos() throws Exception;

    /**
     * Permite obtener un registro de producto correspondiente al identificador especificado en el parametro
     * @param id Long que representa el identificador del producto que se quiere buscar
     * @param cantidad Integer que representa la cantidad del producto
     * @return un objeto Item en caso tal que exista el producto, o null en caso contrario.
     */
    ItemDto obtenerItemPorIdProductoYCantidad(Long id, Integer cantidad) throws Exception;
}
