package it.exercise.TestUnit.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Drumstyle92
 * Spring Boot REST controller class that handles HTTP requests to the application root
 */
@RestController
public class HomeController {

    /**
     * @return Returns a message to the base mapping welcoming the visitor
     * Get method with an HTTP request with string message inside
     */
    @GetMapping("/")
    public String getHello(){

        return "Hello visitor!";

    }

}
