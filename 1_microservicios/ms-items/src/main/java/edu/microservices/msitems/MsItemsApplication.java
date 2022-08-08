package edu.microservices.msitems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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
 * ______________________________________________________________________________________________________________________________
 * Al agregar la dependencia de @EnableCircuitBreaker estamos implementando tolerancia a fallos con hystrix por medio del patron
 * circuit-braker. Tener en cuenta que para la version de spring-boot 2.2.X y 2.3.X se trabaja con hystrix. de la 2.4.X en adelante
 * se trabaja con Resilience4J
 * @author dtrujilloc
 * @version 1.2
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
public class MsItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsItemsApplication.class, args);
	}

}
