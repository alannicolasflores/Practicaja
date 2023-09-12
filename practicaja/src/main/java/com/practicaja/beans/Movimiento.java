package com.practicaja.beans;

public class Movimiento {

    private int idMovimiento;
    private String tipoMovimiento;
    private double importe;
    private String fechaMovimiento;
    private int noCuentaDestino;

    // Constructor por defecto
    public Movimiento() {
    }

    // Constructor con argumentos para inicializar todos los campos
    public Movimiento(int idMovimiento, String tipoMovimiento, double importe, String fechaMovimiento, int noCuentaDestino) {
        this.idMovimiento = idMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
        this.fechaMovimiento = fechaMovimiento;
        this.noCuentaDestino = noCuentaDestino;
    }

    // Encapsulamiento de los campos (getters y setters)
    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getNoCuentaDestino() {
        return noCuentaDestino;
    }

    public void setNoCuentaDestino(int noCuentaDestino) {
        this.noCuentaDestino = noCuentaDestino;
    }
}
