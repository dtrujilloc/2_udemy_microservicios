package edu.microservices.msitems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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
 * @author dtrujilloc
 * @version 1.0
 * ____________________________________________________________________________________________________________________________
 * Al agregar la dependencia de eureka-client en el pom.xml es suficiente para que el ms se registre como cliente automaticamente
 * en servidor de eureka. Pero es recomendable agregar la etiqueta explicita en la clase principal @EnableEurekaClient.
 * @author dtrujilloc
 * @version 1.1
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class MsItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsItemsApplication.class, args);
	}

}
