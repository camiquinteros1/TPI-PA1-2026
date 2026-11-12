package empleado.modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Empleado {

    private UUID id;
    private String nombreCompleto;
    private String legajo;
    private LocalDate fechaIngreso;
    private Puesto puesto;
    private double salario;

    private Empleado(String nombreCompleto, String legajo, LocalDate fechaIngreso, Puesto puesto, double salario) {
        this.id = UUID.randomUUID();
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.fechaIngreso = fechaIngreso;
        this.puesto = puesto;
        this.salario = salario;

    }

    public UUID getId() {
        return id;
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

    public static Empleado crear(String nombreCompleto, String legajo, LocalDate fechaIngreso, Puesto puesto, double salario) {
        return new Empleado(nombreCompleto, legajo, fechaIngreso, puesto, salario);
    }

}
