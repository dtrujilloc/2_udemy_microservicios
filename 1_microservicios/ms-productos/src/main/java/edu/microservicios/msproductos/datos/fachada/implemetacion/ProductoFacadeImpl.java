package edu.microservicios.msproductos.datos.fachada.implemetacion;

import edu.microservicios.msproductos.comun.dto.ProductoDto;
import edu.microservicios.msproductos.datos.fachada.interfaz.IProductoFacade;
import edu.microservicios.msproductos.datos.mapeador.IProductoMapper;
import edu.microservicios.msproductos.datos.model.Producto;
import edu.microservicios.msproductos.datos.repositorio.IProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductoFacadeImpl implements IProductoFacade {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IProductoMapper productoMapper;

    @Override
    public List<ProductoDto> obtenerTodos() {
        log.info(">>> Start method obtenerTodos");
        List<Producto> productoList = productoRepository.findAll();
        List<ProductoDto> productoDtoList = productoMapper.entityListToDtoList(productoList);   // Se transforma de Entity  a DTO
        log.info("<<< End method obtenerTodos");
        return productoDtoList;
    }

    @Override
    public ProductoDto obtenerPorId(Long id) {
        log.info(">>> Start method obtenerPorId");
        Producto producto = productoRepository.findById(id).orElse(null);
        ProductoDto productoDto = productoMapper.entityToDto(producto);
        log.info("<<< End method obtenerPorId");
        return productoDto;
    }
}
