#EJERCICIO 4

Crea un proyecto Spring Boot con las dependencias.

    1 H2
    2 Spring Data JPA
    3 Spring Web
    4 Spring Boot dev tools

Crear una clase HelloController que sea un controlador REST.

Dento de la clase crear un método que retorne un saludo.

Probar que retorna el saludo desde el navegador y desde Postman.

#EJERCICIO 5

Dentro de la misma app crear las clases necesarias para trabajar con "ordenadores".

    1 Laptop (entidad)
    2 LaptopRepository (repositorio)
    3 LaptopController (controlador)

Desde LaptopController crear un método que devuelva una lista de objetos Laptop.

Probar que funciona desde Postman.

Los objetos Laptop se pueden insertar desde el método main de la clase principal.


#EJERCICIO 6

Crear un método en LaptopController que reciba un objeto Laptop enviado en formato JSON 
desde Postman y persistido en la base de datos.

Comprobar que al obtener de nuevo los laptos aparece el nuevo ordenador creado.

#EJERCICIO 7 
Implementar los métodos CRUD en el API REST de Laptop creada en ejercicios anteriores

Los métodos CRUD:

    1 findAll()
    2 findOneById()
    3 create()
    4 update()
    5 delete()
    6 deleteAll()

#EJERCICIO 8
Implementar swagger sobre el API REST de Laptop y verificar que funciona en la URL:

    http://localhost:8080/swagger-ui

#EJERCICIO 9
Crear casos de test para el controlador de Laptop desde Spring Boot

Con click derecho dentro del codigo de la clase LaptopController utilizar la opción
Generate > Test para crar la clase con todos los test unitarios e implemntarlos siguiento
el proceso visto en clase
    