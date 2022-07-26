package edu.microservicios.msproductos.comun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    private Long id;
    private String nombre;
    private Double precio;
    private Date fechaCreacion;
}
