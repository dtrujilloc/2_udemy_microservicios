package edu.microservices.mszuul.filtros;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Clase que extiende de ZuulFilter para poder realizar un filtro personalizado. En este caso vamos a medir o calcular
 * el tiempo que se demora una peticion. Para eso necesitamos un filtro "pre" que tome el tiempo inicial y lo pase por medio
 * de los headers en la peticion para calcular el tiempo final en el filtro "post".
 * @author dtrujilloc
 * @version 1.0
 */
@Slf4j
@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

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
        return "pre";
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
     * En este caso calculara el tiempo inicial de la peticion y lo pasara por el encabezado
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info(">>> Start method run in pre filter");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        Long tiempoInicio = System.currentTimeMillis();

        httpServletRequest.setAttribute("tiempoInicio", tiempoInicio);

        log.info("Peticion de tipo {} enrutada a {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURL().toString());

        log.info("<<< End method run in pre filter");
        return null;
    }
}
