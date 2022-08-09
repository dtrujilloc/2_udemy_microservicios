package edu.microservices.mszuul.filtros;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Clase que extiende de ZuulFilter para poder realizar un filtro personalizado. En este caso vamos a medir o calcular
 * el tiempo que se demora una peticion. Para eso necesitamos un filtro "post" que tome el tiempo inicial que se calculo
 * en el filtro "pre" y realice la operacion necesaria para calcular el tiempo transcurrido.
 * @author dtrujilloc
 * @version 1.0
 */
@Slf4j
@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

    /**
     * El tipo de filtro puede ser:
     *      - pre: filtro antes de enrutar el request
     *      - post: filtro despues de enrutar el request
     *      - route: cuando se enruta el request
     *
     * este tipo de filtro termina cuando se va a ejecutar el filtro
     * @return
     */
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * Este determina si se ejecuta el filtro o no que esta en metodo run. aqui podria existir algun tipo de validacion
     * con la informacion en las cabeceras o demas que permita determinar si se filtra o no.
     *
     * Cuando es True, se ejecuta el filtro, cuando es False, no se ejecuta el filtro
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Metodo que tiene la logica del filtro.
     *
     * En este caso calculara tomara el tiempo inicial pasado pro el encabezado y a partir de este calculara el tiempo
     * transcurrido
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info(">>> Start method run in post filter");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        Long tiempoInicio = (Long) httpServletRequest.getAttribute("tiempoInicio");
        Long tiempoFin = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFin - tiempoInicio;

        log.info("Tiempo Transcurrido {}s o {}ms", (tiempoTranscurrido/1000), tiempoTranscurrido);

        log.info("<<< End method run in post filter");
        return null;
    }
}
