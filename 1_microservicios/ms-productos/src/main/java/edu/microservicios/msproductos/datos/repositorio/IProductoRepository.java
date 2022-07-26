package edu.microservicios.msproductos.datos.repositorio;

import edu.microservicios.msproductos.datos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que permite la conexion con la BD y el mapeo de datos a la entidad o clase Producto
 * @author dtrujilloc
 * @version 1.0
 */
@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
