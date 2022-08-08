package edu.microservices.mszuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Es importante que nuestra puerta de enlace tambien sea cliente de eureka para poder ver el estado de nuestra puerta de enlace.
 *
 * Por otro lado para convertir nuestro proyecto en una puerta de enlace, etiquetamos el proyecto con @EnableZuulProxy y configuramos
 * el properties para ello.
 *
 * @author dtrujilloc
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class MsZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsZuulApplication.class, args);
	}

}
