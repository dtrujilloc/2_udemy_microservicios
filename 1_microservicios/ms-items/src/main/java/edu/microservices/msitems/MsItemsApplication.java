package edu.microservices.msitems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal de la aplicacion, desde aqui arranca la plicacion.
 *
 * - La etiqueta @SpringBootApplication especifica que el proyecto es un proyecto spring-boot, y lo autoconfigura con lo
 * requerido y necesario para ser un proyecto spring-boot.
 *
 * - La etiqueta @EnableFeignClients permite habilitar el autoscaneo de clientes http en nuestra aplicacion que utilicen Feign.
 * es por esta razon que las interfaces que queremos convertir en un cliente http por medio de feign las marcamos para que por
 * medio del autoscaneo se puedan detectar y configurar acordemente.
 * ________________________________________________________________________________________________________________________________
 * Para habilitar el balanceador de carga por medio de ribbon se hace por medio de la etiqueta @RibbonClient especificando el nombre
 * del ms dentro de la etiqueta.
 *
 * Si tuvieramos varios cliente que registrar, lo hariamos con la etiqueta que termina con una "s" al final @RibbonClients. Y dentro
 * especificamos cada uno de los cliente de forma individual separandolos por ","
 */
@SpringBootApplication
@EnableFeignClients
@RibbonClient(name= "ms-producto")
public class MsItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsItemsApplication.class, args);
	}

}
