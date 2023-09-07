package com.practicaja.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/hola")
public class PracticajaController {
    
    @RequestMapping(method = RequestMethod.GET, value = "/saluda")
    public ResponseEntity prueba()
    {
        return new ResponseEntity("HOLA",HttpStatus.OK);
    }
}
