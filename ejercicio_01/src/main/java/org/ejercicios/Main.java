package org.ejercicios;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Crear un proyecto con la dependencia spring-context.
 * Crear una clase org.ejercicios.Saludo con un método imprimirSaludo que imprima un texto por consola
 * Crear el archivo beans.xml con un bean para la clase org.ejercicios.Saludo
 * Obtener el bena desde el método main y ejecutar el método imprimir saludo
 */


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Saludo saludo = (Saludo) context.getBean("saludo");
        saludo.imprimirSaludo("Hola que tal");
    }
}
