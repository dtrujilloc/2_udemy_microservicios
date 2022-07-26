package edu.microservicios.msproductos.negocio.servicio.implementacion;

import edu.microservicios.msproductos.datos.model.Producto;
import edu.microservicios.msproductos.datos.repositorio.IProductoRepository;
import edu.microservicios.msproductos.negocio.servicio.interfaz.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional  // Permite ejecutar todos los llamados de este metodo en una sola transaccion a la BD, si alguna llega a fallar se ejecuta un rollback
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
}
