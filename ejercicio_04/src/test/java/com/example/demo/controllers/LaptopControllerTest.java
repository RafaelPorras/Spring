package com.example.demo.controllers;

import com.example.demo.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Necesario para que Spring cree y acepte las peticiones http
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class LaptopControllerTest {
    /**
     * Preparacion del entorno previo a los test
     */
    //objeto para lanzar las peticiones http
    private TestRestTemplate testRestTemplate;

    //Necesario para que Spring nos inyecte un builder para usar testRestTemplate
    @Autowired
    private RestTemplateBuilder restTemplateBuider;

    //variable sobre la que inyectaremos el puerto aleatorio
    @LocalServerPort
    private int port;

    //método que iniciará el objeto restTemplateBuilder
    @BeforeEach
    void setUp(){
        restTemplateBuider = restTemplateBuider.rootUri("http://localhost:"+port);

        //mediante este objeto haremos las llamadas http
        testRestTemplate = new TestRestTemplate(restTemplateBuider);
    }

    @Test
    void findAll() {
        //realizamos la peticion get a la api para que nos traiga todos los laptops
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops",Laptop[].class);

        //creamos los asserts
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findOneById() {
        Long id = null;
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/"+id,Laptop.class);

        try{
            if(id == 1L){
                assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
            }

            if(id == -1L){
                assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
            }

        }
        catch(NullPointerException e){
           assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        }

    }

    @Test
    void create() {
        //Para poder enviar el json hay que crear las cabeceras necesarias
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //Preparamos el json a enviar
        String json = """
                        {
                            "brand": "Dell",
                            "model": "XS",
                            "screen": "15 pulgadas",
                            "hdd": "1TB",
                            "ram": "8GB"
                        }
                         """;

        //Preparamos la peticion
        HttpEntity<String> request = new HttpEntity<>(json,headers);

        //Ejecutamos la peticion
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST,request,Laptop.class);

        //Obtenemos el body de la peticion
        Laptop laptopResult = response.getBody();

        assertEquals(1L,laptopResult.getId());
        assertEquals("Dell", laptopResult.getBrand());
    }

    @Test
    void update() {
        //Para poder enviar el json hay que crear las cabeceras necesarias
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //Preparamos el json a enviar
        String json = """
                        {
                            "id": 3,
                            "brand": "DellActualizado",
                            "model": "XS",
                            "screen": "15 pulgadas",
                            "hdd": "1TB",
                            "ram": "8GB"
                        }
                         """;


        //Preparamos la peticion
        HttpEntity<String> request = new HttpEntity<String>(headers);

        System.out.println(request);


        //Ejecutamos la peticion
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT,request,Laptop.class);

        System.out.println(response);

        //Obtenemos el body de la peticion
        Laptop laptopResult = response.getBody();



       assertEquals(3L,laptopResult.getId());
       assertEquals("DellActualizado", laptopResult.getBrand());
    }

    @Test
    void delete() {
        Long id = 1L;
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/"+id,Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


    }

    @Test
    void deleteAll() {
        //realizamos la peticion get a la api para que nos traiga todos los laptops
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops",Laptop[].class);

        //creamos los asserts
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}