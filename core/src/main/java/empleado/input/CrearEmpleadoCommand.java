package empleado.input;

import empleado.modelo.Puesto;

import java.time.LocalDate;

public class CrearEmpleadoCommand {
    private final String nombreCompleto;
    private final String legajo;
    private final LocalDate fechaIngreso;
    private final Puesto puesto;
    private final double salario;

    public CrearEmpleadoCommand(String nombreCompleto, String legajo, LocalDate fechaIngreso, Puesto puesto, double salario) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.fechaIngreso = fechaIngreso;
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public String getLegajo() {
        return legajo;
    }
    public LocalDate getFechaIngreso() {
        return fechaIngreso;  }
    public Puesto getPuesto() {
        return puesto;
    }
    public double getSalario() {
        return salario;
    }

}
