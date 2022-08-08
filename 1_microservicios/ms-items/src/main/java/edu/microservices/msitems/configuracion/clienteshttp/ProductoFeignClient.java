package edu.microservices.msitems.configuracion.clienteshttp;

import edu.microservices.msitems.comun.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Interface que contiene la configuracion necesaria para que funcione como un cliente http con Feign.
 * Esta interface permite la conexion con el microservicio de productos.
 * @author dtrujilloc
 * @version 1.0
 * _____________________________________________________________________________________________________________________
 * Al estar trabajando con eureka, ya no es necesario especificar la url en el feignClient ya que eureka se encarga de
 * darme a conocer la localizacion o url del ms que concuerde con el nombre especificado.
 * @author dtrujilloc
 * @version 1.1
 */

@FeignClient(name = "ms-producto")  // version 1.1
//@FeignClient(name = "ms-producto", url="http://localhost:8081") // version 1.0
public interface ProductoFeignClient {

    @GetMapping("/productos")
    List<ProductoDto> obtenerProductoTodos();

    @GetMapping("/productos/{id}")
    ProductoDto obtenerProductoPorId(@PathVariable Long id);
}
