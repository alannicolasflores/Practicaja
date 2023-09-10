package com.practicaja.beans;

public class Cuenta {

    private int noCuenta;
    private String noTarjeta;
    private double saldo;
    private int idBanco;
    private String nombreBanco;
    private int idUsuario;
    private String nombre;
    private String aPaterno;
    private String aMaterno;

    // Constructor por defecto
    public Cuenta() {
    }

    // Constructor con argumentos para inicializar todos los campos
    public Cuenta(int noCuenta, String noTarjeta, double saldo, int idBanco, String nombreBanco, int idUsuario, String nombre, String aPaterno, String aMaterno) {
        this.noCuenta = noCuenta;
        this.noTarjeta = noTarjeta;
        this.saldo = saldo;
        this.idBanco = idBanco;
        this.nombreBanco = nombreBanco;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
    }

    // Encapsulamiento de los campos (getters y setters)
    public int getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(int noCuenta) {
        this.noCuenta = noCuenta;
    }

    public String getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(String noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }
}
