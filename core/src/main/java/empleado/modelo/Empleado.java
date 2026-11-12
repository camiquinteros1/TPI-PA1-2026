package empleado.modelo;

import empleado.exception.CampoObligatorioException;
import empleado.exception.FechaIngresoFuturaException;

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

        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new CampoObligatorioException("El nombre completo no puede ser nulo o vacío.");
        }
        if (legajo == null || legajo.isBlank()) {
            throw new CampoObligatorioException("El legajo no puede ser nulo o vacío.");
        }
        if (fechaIngreso == null) {
            throw new CampoObligatorioException("La fecha de ingreso no puede ser nula.");
        }
        if (puesto == null) {
            throw new CampoObligatorioException("El puesto no puede ser nulo.");
        }

        if (fechaIngreso.isAfter(LocalDate.now())) {
            throw new FechaIngresoFuturaException("La fecha de ingreso no puede ser futura.");
        }


        return new Empleado(nombreCompleto, legajo, fechaIngreso, puesto, salario);
    }

}
