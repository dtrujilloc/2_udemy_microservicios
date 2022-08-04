package edu.microservices.mseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Se configura este proyecto como un servidor de registro de microservicios (Eureka-Server). Esto se logra gracias a las
 * dependencias agregadas en el pom.xml y a la anotacion @EnableEurekaServer
 * @author dtrujilloc
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class MsEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEurekaApplication.class, args);
	}

}
