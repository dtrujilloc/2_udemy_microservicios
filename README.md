# Arquitectura Microservicios - JAVA - Spring Boot - Spring Cloud

El objetivo de este repositorio es brindar y demostrar un proceso de construccion de una arquitectura de microservicios gracias a los conceptos explicados en el [curso de microservicios sobre spring boot y spring cloud de plataforma UDEMY](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/).

En la rama master se podra tener todo el proyecto desarrollado con todas las funcionalidades propuestas. Pero tambien se hara un rama por cada una de las funcionalidades y configuraciones, en donde se podra detallar
solo los cambios y configuraciones empleadas para lograr el objetivo.

En la rama master se podra tener todo el proyecto desarrollado con todas las funcionalidades propuestas. Pero tambien se hara un rama por cada una de las funcionalidades y configuraciones, en donde se podra detallar solo los cambios y configuraciones empleadas para lograr el objetivo.

A continuación, cada uno de los pasos realizados:

## 1 Microservicios
un microservicios es un conjunto de funcionalidades encapsuladas que tienen un proposito o un contexto en comun. Por ejemplo, si yo tengo un microservicio de usuario, pues las funcionalidaes que voy a tener seran funcionalidades relacionadas con el usuario. Se puede tener varios microservicios y estos comunicarse entre si para cumplir con una funcionalidad o proposito. Cada microservicio es independiente.

Cabe aclarar que un microservicio en spring boot, no es mas que un proyecto comun y corriente el cual se configura de tal forma que exponga sus funcionalides por medio de URI's. en pocas palabras un proyecto en spring boot que funcione como una API RESTFull.

Para este proyecto creamos dos microservicios:
- ms-productos: tiene como proposito almacenar la informacion de los productos y exponerla por medio de una API RESTFull. Se utilizo un BD en memoria (H2).
- ms-items: tiene como proposito recrear la funcion de un microservicios, desde el cual se consultara la informacion de los productos y se tendra el cuenta un parametro adicional que es la cantidad del producto. Esto se realiza con el proposito de tener dos microservicios y ver como es la comunicacion entre ellos.

### 1.1 Clientes HTTP - Rest Template y Feign
Un Cliente HTTP es quien realiza peticiones http. en un proyecto o aplicacion en Spring-Boot lo podemos considerar como una configuracion o una herramienta que permite realizar peticiones HTTP ya sea hacia un servidor o incluso hacia otra aplicacion.

Como estamos en una arquitectura de microservicios, y se necesita que estos se comuniquen entre si,  la forma de realizar esta comunicacion es por medio de clientes http. Existen multiples clientes HTTP, pero en este caso utilizaremos dos.

- RestTemplate: herramienta que ofrece el framework de spring para la comunicacion http.
Se necesita una clase de configuracion y se configura por medio de un Bean. Esto permite inyectar el objeto de RestTemplate donde se necesita la comunicacion.
- Feign: herramienta que ofrece spring cloud para la comunicacion http.
Ofrece una configuracion por medio de abstraccion de interfaces, lo que permite una configuracion mas facil y rapida. Ademas al ser una herramienta del ecosistema de spring-cloud, permite una integracion casi transparente con todas las herramientas que spring-cloud ofrece.
Es necesario tambien agregar la dependencia acorde para la utilizacion de feign.

En el proyecto de ms-items se pueden apreciar la implementacion de los dos clientes http.
- Por el lado de RestTemplate, se configuro en la clase RestTemplateConfig, y se utilizo en el servicio ProductoRestTemplateServiceImpl.
- Por el lado de Feign, la interface encargada de la conexion http, es la interface ProductoFeignClient, y donde se utiliza es en el servicio ProductoFeignServiceImpl.

### 1.2 Balanceador de Carga - Ribbon
Cuando se trabaja con microservicios, es posible tener mas de una instancia de un mismo microservicio. Una instancia de un MS es tener el proyecto en ejecucion, entonces cuando se habla de mas instancias, es como tener varias veces el proyecto ejecutandose por puertos diferentes, esto permite que cuando existan muchas peticiones o solicitudes al MS, pues este tenga los recursos suficientes (instancias) como para suplir todas la solicitudes. Perooo... como saber como distribuir o repartir las solicitudes entre las diferentes instancias? Pues esto es el trabajo del balanceador de carga. El load balancer (balanceador de carga) se encarga de saber a cual instancia enviar la solicitud dependiend de como se encuentren en el momento de la solicitud, esto permite el balanceo correcto y acorde y evitar la saturacion de las intancias de los MS.

Para este caso utilizamos Ribbon, el cual es un balanceador de carga. Para esta practica modificamos la version de spring boot(2.3.12) y spring cloud(Hoxton.SR12) en nuesto ms-item, ya que ribbon no esta disponible desde la version 2.4.X. Este balanceador de carga (ribbon) se utiliza sin la necesidad de utiliar Eureka.

En eta practica implementamos Ribbon tanto para cliente http de feign como para RestTemplate. Estos cambios solo quedaran en la rama especifricada y no los llevaremos a la rama master ya que es una propuesta de como utilizar un balanceador de carga sin Eureka, y no se utilizara en el desarrollo del proyecto.

## 2 Eureka
En una arquitectura de microservicios, es normal llegar a un punto o momento donde tengamos muchos microservicios activos junto con las multiples instancias que puede tener cada uno. Ahora pensando en ese contexto, seria muy tedioso estar revisando cada uno de los MS's si esta funcionando correctamente o no. Debido a esta situacion, Netflix, creo un servidor que se encarga de centralizar el registro de los MS's y permitir la visualizacion del estado de cada uno de los MS's.

Eureka, es una herramienta que permite registra y localizar los MS's. Funciona creando un proyecto de spring boot basico, y con tan solo agregarle la dependencia y etiquetarlo como servidor de eureka, ya esta listo para ser utilizado.

La dependencia para configurar un proyecto como servidor es la siguientes:

	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
	</dependency>

Desde Java 9 en adelante, es necesario agregar otra dependencia cuando se trabaja con Eureka. la cual es:

	<dependency>
		<groupId>org.glassfish.jaxb</groupId>
		<artifactId>jaxb-runtime</artifactId>
	</dependency>

Y para finalizar la configuracion del servidor de Eureka, es necesario agregar la etiqueta de @EnableEurekaServer en la clase principal del proyecto. Si no modificamos el puerto del proyecto, por defecto, Eureka se despliega en el puerto 8761. Pero si modificamos el puerto del proyecto, pues Eureka tomara dicho puerto para desplegarse. Podemos visualizar el dashboard que ofrece eureka accediendo a la url correspondiente: 

	http://localhost:{puerto_eureka_server}
	
Por otro lado, se tiene la configuracion de los MS's como clientes de Eureka para que una vez se ejecuten los proyectos, se registren automaticamente en el Servidor. Para esto es indispensable en cada que en cada uno de los MS's se agregue la dependencia correspondiente como cliente de Eureka:

	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>
	
El siguiente paso es etiquetar el proyecto como un cliente de Eureka, y esto se hace en la clase principal del poyecto con la etiqueta @EnableEurekaClient.

Por ultimo, falta especificar la ruta del servidor de Eureka, para  que el MS sepa donde tiene que apuntar para registrarse como cliente. Para esto se agrega la siguiente propiedad en archivo de properties (en nuestro caso, nosotros tenemos el servidor eureka expuesto por el puerto 8080).

	eureka.client.service-url.defaultZone=http://localhost:8080/eureka

Este tipo de configuraciones permite que cuando un MS se registre en el servidor de Eureka conozca todos los MS que estan registrados, esto con el proposito de que cada uno de los MS's tenga conocimiento de los demas MS's.

Por otro lado tambien hay que aclarar que eureka tiene incorporado el balanceador de carga automaticamente. es decir, no necesita configuracion de balanceador de carga, ya que eureka es encarga de detectar las instancias registradas de cada uno de los MS y de distribuir automaticamente las peticiones entre las instancias disponibles. Para las versiones desde spring boot 2.3.X hacia atras, incluyendola, eureka trabaja con "ribbon" como balanceador de carga. Y desde la 2.4.X en adelante, incluyendola, trabaja con "load balancer" como balanceador de carga.

## 3 Hystrix
Anteriormente mencionamos que los microservicios son independencientes pero pueden estar comunicados para solventar una necesidad o funcionalidad. Como en nuestro caso, que tenemos dos MS, ms-items y ms-productos, el ms-items llama al ms-productos para obtener la informacion necesaria para poder responder adecuadamente. Pero entonces que pasaria si a la hora de hacer la peticion en el ms-items, la comunicacion entre los dos MS falla? tendriamos un fallo en cascada ya que la respuesta de items depende de lo que responda productos. Pues en el ecosistema de spring cloud existe una herramienta llamada Hystrix, permitiendo agregar la funcionalidad de tolerancia a fallos a nuestra arquitectura de microservicios. Esta tolerancia a fallas se basa en un patron [Circuit Breaker](https://apiumhub.com/es/tech-blog-barcelona/patron-circuit-breaker/). Este patron en pocas palabras evita seguir llamando al MS que esta fallando momentanemente, esperando un tiempo determinado para volver a intentar llamar nuevamente al MS. Lo que se puede hacer mientras que el Ms falla, es configurar un respuesta falsa para asi evitar propagar el error.

Para configurar Hystrix, es necesario agregar la dependencia acorde:

	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
	</dependency>
	
Esta dependencia tiene que agregarse en el proyecto que llama a otros MS's. En nuestro caso en el ms-items, ya que este llama o se comunica con el ms-productos.

Por otro lado, es importante etiquetar el proyecto para habilitar el patron de Circuit Breaker. Esto se hace colocando la etiqueta @EnableCircuitBreaker en la clase principal del proyecto.

Para programar una respuesta falsa sobre el proceso o funcionalidad que falle, es necesario etiquetar el metodo que puede fallar con la etiqueta @HystrixCommand(fallbackMethod = "{nombre_del_metodo_falso}") para indicar que metodo tiene que llamarse cuando el metodo principal falle. Por ejemplo, en nuestro caso, en el ms-items, existe un endpoint que permite consultar un item, los parametros son el idProducto y cantidad. Se busca el producto en el ms-producto por medio del idProducto y se utiliza la informacion de este para construir el objeto de item teniendo en cuenta la cantidad enviada por parametro. Lo que hicimos fue que cuando se enviara un identificador de un producto que no existiera en la BD, el ms-producto respondiera con una excepcion, simulando un error. por medio de hystrix, se programo un flujo falso, para cuando este escenario de error se presentara, lo que se hizo fue programar un metodo que contruyera un objeto de item temporal o momentaneo y que esa fuera la respuesta a la peticion.

Hystrix fue creado inicialmente como herramienta de tolerancia a fallos, por lo que actualmente funciona para la version de spring boot 2.3.X hacia atras. desde la version 2.4.X en adelante se utiliza Resilience4j como herramienta de tolerancia a fallos. Por esta razon no vamos a combinar los cambios de la rama de Hystrix en la rama master.
