package com.practicaja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practicaja.beans.Cuenta;
import com.practicaja.beans.Movimiento;
import com.practicaja.services.PracticajaServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = "/controlador")
public class PracticajaController {
	@Autowired
	private PracticajaServices pc;

	HttpSession sesion = null;
	
	@RequestMapping(value = "/saludo", method = RequestMethod.GET)
	public ResponseEntity saludar(HttpServletRequest request)
	{		
		try {
			sesion = request.getSession();
			Cuenta nombre = (Cuenta) sesion.getAttribute("datosCuenta");
			String respuesta = "Holaaa " + nombre.getNombre() + nombre.getaPaterno();
			return new ResponseEntity(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("No existe sesion", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	@RequestMapping(value = "/consultarSaldo", method = RequestMethod.GET)
	public ResponseEntity consultarSaldo(@RequestParam String noTarjeta, @RequestParam String NIP,HttpServletRequest request)
	{		
		Cuenta datosCuenta = null;
		try {
			datosCuenta = pc.consultarSaldo(noTarjeta, NIP);
			sesion = request.getSession();
			sesion.setAttribute("datosCuenta", datosCuenta);
			return new ResponseEntity(datosCuenta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e, HttpStatus.OK);
		}		
	}

	@RequestMapping(value = "/retirar", method = RequestMethod.POST)
	public ResponseEntity retirar(@RequestParam double montoRetiro, HttpServletRequest request)
	{		
		double saldoFinal = 0;
		Cuenta datosCuenta = null;
		try {
			sesion = request.getSession();
			datosCuenta = (Cuenta) sesion.getAttribute("datosCuenta");
			if(datosCuenta != null)
			{
				saldoFinal = pc.retirar(montoRetiro,datosCuenta);
				sesion.setAttribute("saldoFinal", saldoFinal);
				return new ResponseEntity(saldoFinal, HttpStatus.OK);
			}
			else{
				return new ResponseEntity("No existe sesión", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@RequestMapping(value = "/consultaCuentaDestino", method = RequestMethod.GET)
	public ResponseEntity consultaCuentaDestino(@RequestParam String noTarjetaCuenta,HttpServletRequest request)
	{		
		Cuenta cuentaDestino = null;
		try {
			sesion = request.getSession();
			cuentaDestino = pc.consultaCuentaDestino(noTarjetaCuenta);
			sesion.setAttribute("cuentaDestino", cuentaDestino);
			return new ResponseEntity(cuentaDestino, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@RequestMapping(value = "/depositar", method = RequestMethod.POST)
	public ResponseEntity depositar(@RequestParam double montoDeposito,HttpServletRequest request)
	{		
		Movimiento movimiento = null;
		Cuenta cuentaDestino = null;
		try {
			sesion = request.getSession();
			cuentaDestino = (Cuenta) sesion.getAttribute("cuentaDestino");
			if(cuentaDestino != null){
				movimiento = pc.depositar(montoDeposito, cuentaDestino);			
				return new ResponseEntity(movimiento, HttpStatus.OK);
			}else{
				return new ResponseEntity("No existe sesión", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
