package edu.microservicios.msproductos.datos.fachada.interfaz;

import edu.microservicios.msproductos.comun.dto.ProductoDto;
import edu.microservicios.msproductos.datos.model.Producto;

import java.util.List;

/**
 * Interface que emplea el patron fachada para dar disponibilizar todas las funcionalidades correspondiente pero devolviendo un objeto DTO
 * @author dtrujilloc
 * @version 1.0
 */
public interface IProductoFacade {

    /**
     * Permite obtener todos los registros de la tabla Producto
     * @return
     */
    List<ProductoDto> obtenerTodos();

    /**
     * Permite obtener un registro correspondiente al identificador especificado en el parametro
     * @param id Long que representa el identificador del producto que se quiere buscar
     * @return un objeto Producto en caso tal que exista, o null en caso contrario.
     *
     */
    ProductoDto obtenerPorId(Long id);
}
