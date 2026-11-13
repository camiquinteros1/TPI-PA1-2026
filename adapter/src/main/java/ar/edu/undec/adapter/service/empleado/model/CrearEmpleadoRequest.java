package ar.edu.undec.adapter.service.empleado.model;

import empleado.modelo.Puesto;

import java.time.LocalDate;

public class CrearEmpleadoRequest {

    private String nombreCompleto;
    private String legajo;
    private LocalDate fechaIngreso;
    private Puesto puesto;
    private double salario;

    public CrearEmpleadoRequest() {
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getLegajo() {
        return legajo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}