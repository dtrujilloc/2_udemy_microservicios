package edu.microservices.msitems.configuracion.clienteshttp;

import edu.microservices.msitems.comun.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "ms-producto", url = "http://localhost:8081")
public interface ProductoFeignClient {

    @GetMapping("/productos")
    List<ProductoDto> obtenerProductoTodos();

    @GetMapping("/productos/{id}")
    ProductoDto obtenerProductoPorId(@PathVariable Long id);
}
