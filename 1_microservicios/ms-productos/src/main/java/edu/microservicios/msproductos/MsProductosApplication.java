package edu.microservicios.msproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Clase principal de la aplicacion, desde aqui arranca la plicacion.
 *
 * - La etiqueta @SpringBootApplication especifica que el proyecto es un proyecto spring-boot, y lo autoconfigura con lo
 * requerido y necesario para ser un proyecto spring-boot.
 * @author dtrujilloc
 * @version 1.0
 * ____________________________________________________________________________________________________________________________
 * Al agregar la dependencia de eureka-client en el pom.xml es suficiente para que el ms se registre como cliente automaticamente
 * en servidor de eureka. Pero es recomendable agregar la etiqueta explicita en la clase principal @EnableEurekaClient.
 * @author dtrujilloc
 * @version 1.1
 */
@SpringBootApplication
@EnableEurekaClient
public class MsProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductosApplication.class, args);
	}

}
