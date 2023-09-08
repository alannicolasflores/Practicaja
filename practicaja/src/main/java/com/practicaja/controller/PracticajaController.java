package com.practicaja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.practicaja.services.PracticajaServices;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = "/controlador")
public class PracticajaController {
	@Autowired
	private PracticajaServices pc;
	
	@RequestMapping(value = "/saludo", method = RequestMethod.GET)
	public ResponseEntity saludar()
	{
		
		String respuesta = "Holaaa " + pc.saluda();
		return new ResponseEntity(respuesta, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/hola", method = RequestMethod.GET)
	public String hola()
	{
		
		String respuesta = "Holaaa";
		return respuesta;
	}
}
