# CustomersWeb

Requisitos
---------
Java 11 Amazon Correto
Maven
BD H2 (Incluido en la aplicación, lo registros se guardan de manera temporal)

Desarrollo
-----------
* Descargar los fuentes del repositorio:
  
* En la consola ejecutar:
  mvn clean install
   
* Importar el proyecto en Eclipse

Instación
---------
* Generar Jar
  mvn clean package

* Copiar el archivo "customers-api-1.0.0.jar" generado en la ruta "..\customers-api\target", a la ruta D:/CUSTOMERS
* Abrir una consola CMD, colocarse en la ruta y luego ejecutar el Jar
  D:/CUSTOMERS > java -jar customers-api-1.0.0.jar

* Esperar que cargue y luego probar en el navegador la ruta:
  http://localhost:8081/customers-api/h2-console

* Probar el servicio desde el Postman
