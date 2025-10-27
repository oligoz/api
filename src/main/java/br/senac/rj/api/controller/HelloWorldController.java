package br.senac.rj.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping
    public String olaMundo() {
        return "Hello World again!";
    }

    @GetMapping("/{seuNome}")
    public String olaVoce(@PathVariable String seuNome) {
        if (seuNome != null) {
            seuNome += "!";
        } else {
            seuNome = "Não recebi nome";
        }
        return "Hello, " + seuNome;
    }
}
