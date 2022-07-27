package edu.microservicios.msproductos.datos.mapeador;

import edu.microservicios.msproductos.comun.dto.ProductoDto;
import edu.microservicios.msproductos.datos.model.Producto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interface que encarga de construir el mapeador para los productos. Se encarga de tranformar la info de Entidad a DTO
 * @author dtrujilloc
 * @version 1.0
 *
 * Nota: en etiqueta @Mapper, especificamos el atributo componentModel, el cual es para especificar quien es el que genera la implementacion
 * automatica de la interface, cuando especificamos que sea spring, la implementacion la genera spring, creando un Bean y por consiguiente se
 * puede inyectada por medio de @Autowired. cuando se especifica este atributo, la implemetacion se realiza por defecto y la forma de obtener
 * una instancia del maper es de la siguiente manera "Mappers.getMapper(Class)".
 */
@Mapper(componentModel = "spring")
public interface IProductoMapper {

    ProductoDto entityToDto(Producto entity);

    List<ProductoDto> entityListToDtoList(List<Producto> entityList);

    Producto dtoToEntity(ProductoDto dto);

    List<Producto> dtoListToEntityList(List<ProductoDto> dtoList);
}
