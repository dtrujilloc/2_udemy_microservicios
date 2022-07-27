package edu.microservices.msitems.comun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el DTO de item, especifica el producto y la cantidad de producto, junto con su valor total segun
 * el precio y cantidad del producto
 * @author dtrujilloc
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private ProductoDto producto;

    private Integer cantidad;

    public Double getTotal() {
        return producto.getPrecio() * cantidad.doubleValue();
    }
}
