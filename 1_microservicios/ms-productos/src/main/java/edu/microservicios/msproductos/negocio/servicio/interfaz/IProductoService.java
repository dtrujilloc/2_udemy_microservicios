package edu.microservicios.msproductos.negocio.servicio.interfaz;

import edu.microservicios.msproductos.datos.model.Producto;

import java.util.List;

/**
 * Interface que representa el servicio de producto, es decir donde estara la logica de negocio relaiconado a producto
 * @author dtrujilloc
 * @version 1.0
 */
public interface IProductoService {

    /**
     * Permite obtener todos los registros de la tabla Producto
     * @return
     */
    List<Producto> obtenerTodos();

    /**
     * Permite obtener un registro correspondiente al identificador especificado en el parametro
     * @param id Long que representa el identificador del producto que se quiere buscar
     * @return un objeto Producto en caso tal que exista, o null en caso contrario.
     */
    Producto obtenerPorId(Long id);
}
