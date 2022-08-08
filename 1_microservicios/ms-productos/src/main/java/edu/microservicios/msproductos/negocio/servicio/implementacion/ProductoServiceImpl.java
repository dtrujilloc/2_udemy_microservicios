package edu.microservicios.msproductos.negocio.servicio.implementacion;

import edu.microservicios.msproductos.comun.dto.ProductoDto;
import edu.microservicios.msproductos.datos.fachada.interfaz.IProductoFacade;
import edu.microservicios.msproductos.negocio.servicio.interfaz.IProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProductoServiceImpl implements IProductoService {

    /**
     * Se accede a las propiedades por medio de la etiqueta @Value
     */
    @Value("${server.port}")
    private Integer puerto;

    @Autowired
    private IProductoFacade productoFacade;

    @Override
    @Transactional  // Permite ejecutar todos los llamados de este metodo en una sola transaccion a la BD, si alguna llega a fallar se ejecuta un rollback
    public List<ProductoDto> obtenerTodos() {
        log.info(">>> Start method obtenerTodos");
        List<ProductoDto> productoDtoList = productoFacade.obtenerTodos();

        productoDtoList.forEach(productoDtoTemp -> productoDtoTemp.setPuerto(puerto));

        log.info("<<< End method obtenerTodos -> productoDtoListSize:{}", productoDtoList.size());

        return productoDtoList;
    }

    @Override
    @Transactional
    public ProductoDto obtenerPorId(Long id) {
        log.info(">>> Start method obtenerPorId -> productId:{}", id);
        ProductoDto productoDto = productoFacade.obtenerPorId(id);

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("<<< End method obtenerPorId -> producto:{}", productoDto);
        return productoDto;
    }
}
