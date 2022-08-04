package edu.microservicios.msproductos.negocio.controlador;

import edu.microservicios.msproductos.comun.dto.ProductoDto;
import edu.microservicios.msproductos.datos.model.Producto;
import edu.microservicios.msproductos.negocio.servicio.interfaz.IProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public List<ProductoDto> obtenerProductoTodos() {
        log.info(">>> Start endpoint obtenerProductoTodos");
        List<ProductoDto> productoDtoList = productoService.obtenerTodos();
        log.info("<<< End endpoint obtenerProductoTodos");
        return productoDtoList;
    }

    @GetMapping("/{id}")
    public ProductoDto obtenerProductoPorId(@PathVariable Long id) {
        log.info(">>> Start endpoint obtenerProductoPorId");
        ProductoDto productoDto = productoService.obtenerPorId(id);
        log.info(">>> End endpoint obtenerProductoPorId");
        return productoDto;
    }
}
