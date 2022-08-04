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