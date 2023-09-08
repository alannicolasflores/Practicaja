package com.practicaja.common;

public class Exception extends RuntimeException{
	private String folio;
	private String mensaje;
	
	public Exception(Throwable throwable) {
		super(throwable);
	}
	
	public Exception(String msg) {
		super(msg);
	}
	
	public Exception(String msg, Throwable throwable) {
        super(msg, throwable);
    }

	
	public Exception(String folio, String mensaje){
		super(mensaje);
		this.folio = folio;
		this.mensaje = mensaje;
		
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "{\"Exception\":{\"folio\":\"" + folio + "\", \"mensaje\":\"" + mensaje + "\"}}";
	}
}
