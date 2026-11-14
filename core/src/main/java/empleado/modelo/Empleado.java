package empleado.modelo;

import empleado.exception.*;

import java.time.LocalDate;
import java.util.UUID;

public class Empleado {

    private UUID id;
    private String nombreCompleto;
    private String legajo;
    private LocalDate fechaIngreso;
    private Puesto puesto;
    private double salario;

    private Empleado(UUID id, String nombreCompleto, String legajo, LocalDate fechaIngreso, Puesto puesto, double salario) {
        this.id = id;
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

        if(legajo == null || !legajo.matches("^[A-Z]{3}\\d{4}$")) {
            throw new empleado.exception.FormatoLegajoInvalidoException("El legajo debe tener el formato de 3 letras mayúsculas seguidas de 4 dígitos.");
        }

        if (salario <=0) {
            throw new SalarioInvalidoException("El salario debe ser mayor a cero.");
        }

        if (salario < puesto.getSalarioMinimo()) {
            throw new SalarioInferiorAlPisoException("El salario no puede ser menor al salario mínimo del puesto.");
        }



        return new Empleado(UUID.randomUUID(), nombreCompleto, legajo, fechaIngreso, puesto, salario);
    }


    public static Empleado reconstruir(UUID id, String nombreCompleto, String legajo,
                                       LocalDate fechaIngreso, Puesto puesto, double salario) {
        return new Empleado(id, nombreCompleto, legajo, fechaIngreso, puesto, salario);
    }

}
