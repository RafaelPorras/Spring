package com.ejerciciosSesiones;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Crear clase NotificationServicie, con un método que imprima un saludo
 * Crear clase UsarService que tenga un atributo de clase NotificationService
 * Utilizar la anotacion @Component en cada clase
 * Habilitar el escaneo de clases en el archivo beans.xml
 * Desde el método main, probar a obtener el bean UserService y ejecutar el
 * método imprimirSaludo que tiene asociado el atributo de tipo NotificationService.
 * De forma similar a la realizada en clase
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService service = (UserService) context.getBean("userService");
        service.notification.imprimirSaludo("Hola desde User Service");

    }
}
