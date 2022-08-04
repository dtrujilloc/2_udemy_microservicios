package edu.microservices.msitems.configuracion.clienteshttp;

import edu.microservices.msitems.comun.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Interface que contiene la configuracion necesaria para que funcione como un cliente http con Feign.
 * Esta interface permite la conexion con el microservicio de productos.
 * @author dtrujilloc
 * @version 1.0
 * _____________________________________________________________________________________________________________________
 * Anteriomente se especifico la URL del microservicio de productos en la etiqueta @FeignClient por medio del valor
 * "url" pero para trabajar con ribbon (balanceador de carga). se especifican estas url's en el archivo de propiedades.
 *
 * Tener en cuenta que ribbon es un balanceador de carga que se utiliza cuando no se trabaja con Eureka (registrador de
 * microservicios) ya que con Eureka este balanceo de carga es transparente.
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
