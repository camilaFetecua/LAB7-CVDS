# *LABORATORIO 8 - MYBATIS-GUICE-PRIMEFACES - 2021-1*

## **Maria Camila Fetecua – Daniel Alejandro Mejia**


## PARTE I. INICIO EN CLASE

**1.Actualice el proyecto del taller realizados en el ejercicio anterior. Agregue las clases y excepciones no descritas de ser necesario.


**8.Pruebe el programa ‘Main’ suministrado, y con este rectifique que a través de la capa lógica se pueda consultar un cliente.

**9.Implemente los métodos que sean necesarios en las interfaces de las entidades (DAO) y en sus implementaciones haciendo uso del DAO inyectado. Haga un programa para comprobar que la consulta de un cliente se haga correctamente, a través de la capa lógica.

## PARTE II - PRUEBAS

**2. Tenga en cuenta: las operaciones que impliquen registrar o actualizar registros, demarcar la transaccionalidad con la anotación @Transactional.

**2Seguir las instrucciones que se encuentran en el repositorio de forma que en la clase MyBatisExample.java se creen los mappers necesarios y sea posible realizar la ejecución de diferentes sentencias SQL en la base de datos de pruebas.

## PARTE III - CAPA PRESENTACIÓN

**1.Realice los cambios necesarios en el archivo pom.xml de tal forma que el proyecto se construya de manera correcta como una aplicación WEB, incluyendo las dependencias (jstl, jsf-api, jsf-impl, primefaces, etc) y los plugins (maven war, tomcat7 maven, etc.).

**2.Agregue el archivo web.xml requerido con la configuración necesaria

**5. Implementar la aplicación Web que permita agregar nuevos clientes a la videotienda, y registrar alquileres para los mismos. Ambas funcionalidades estarán en dos vistas diferentes (registrocliente.xhtml, registroalquiler.xhtml), de acuerdo con las siguientes especificaciones (tenga en cuenta que, por ahora, la aplicación no maneja ningún esquema de autenticación):

**1.La vista de ‘registro de clientes’ debe (1) mostrar el listado paginado de los clientes registrados hasta el momento (con la opción de selecciar de uno de éstos), y (2) debe mostrar los campos para poder registrar un nuevo cliente (con su respectivo botón de registro). Cuando se registre un nuevo cliente, se deberá mostrar automáticamente el nuevo cliente en la parte superior.

**2.Cuando se seleccione uno de los usuarios ya creados, se debe redirigir al usuario a la vista de ‘registro de alquileres’. En esta vista, dado el cliente seleccionado, se debe (1) mostrar los items que no ha regresado, junto con el valor de la multa total asociada a los mismos a la fecha (fecha del sistema), y (2), debe permtir registrar un nuevo alquiler ingresando el código del item (asumiendo que éste se ingresará con un lector de código de barras), el número de días del alquiler, y mostrando el costo del alquiler antes de su confirmación. En el momento que se confirme, se debe volver a la página anterior (registro de clientes).


**3.Ambas vistas se basarán en el ManagedBean de sesión ‘AlquilerItemsBean’ que debe extender ‘BasePageBean’, el cual -a su vez- hace uso de la interfaz ‘ServiciosAlquiler’ (no agregar directamente una implementación concreta, esto se realizará en la configuración de Guice).


**4.El desarrollo de ambas vistas debe quedar distribuido entre los dos desarrolladores de la siguiente manera:

Desarrollador 1: Vista registro de cliente.
Desarrollador 2: Vista registro de alquiler.
Desarrollador 1 y 2: ManagedBean ‘AlquilerItemsBean’.
Cada integrante debe realizar su propio commit pues después se verificarán los cambios de cada uno.
Nota. Para ver cómo navegar entre vistas con JSF revise este enlace.

**6.Construya y despliegue la aplicación con el comando mvn tomcat7:run y realice pruebas de la presentación, que debe estar funcionando correctamente, con la implementación ‘Stub’ del servicio de alquiler.

**7.Modifique la configuración de Guice para asociar a la interfaz, el servicio concreto de alquileres, de forma que todos los cambios que se realicen en la presentación, se actualicen en base de datos de manera correcta.

**8.Realice los ajustes necesarios para que la aplicación funcione de manera correcta y se asegure que todos los métodos están realizando las operaciones sobre la base de datos.



## PARTE IV - ENTREGA CONTINUA
**1.Realice toda la configuración necesaria de CircleCI y Heroku para que la aplicación se construya y despliegue de manera automática cada que se realice un commit al repositorio.

**2.Realice también todas las configuraciones necesarias de Codacy y los ajustes necesarios para obtener una calificación satisfactoria.

**3.Verifique que la aplicación se despliegue correctamente en Heroku y sea completamente funcional, tal como se encontraba en local.

**4.Agregue en el Readme los enlaces necesarios a Heroku, Codacy, etc. para que se pueda verificar el correcto funcionamiento de toda la aplicación
