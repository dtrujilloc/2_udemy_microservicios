package edu.microservicios.msproductos.data.repository;

import edu.microservicios.msproductos.data.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que permite la conexion con la BD y el mapeo de datos a la entidad o clase Producto
 * @author dtrujilloc
 * @version 1.0
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
