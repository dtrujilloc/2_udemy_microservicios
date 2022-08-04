package edu.microservices.msitems.configuracion;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Clase que permite la configuracion y registro del RestTemplate como un Bean
 * @author dtrujilloc
 * @version 1.0
 */
@Configuration
public class RestTemplateConfig {

    @Bean("restTemplateClient")   // Se especifica el nombre del bean para saber cual tiene que buscar cuando se inyecte por medio del Autowired
    @LoadBalanced   // Se especifica que RestTemplate utilizara el balanceador de carga de ribbon
    public RestTemplate registrarRestTemplate() {
        return new RestTemplate();
    }
}
