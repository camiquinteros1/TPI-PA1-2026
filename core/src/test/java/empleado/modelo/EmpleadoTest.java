package empleado.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import empleado.exception.*;


public class EmpleadoTest {

    @Test
    public void crearEmpleadoConDatosValidos(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        Empleado empleado = Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario);

        assertNotNull(empleado.getId());
        assertEquals(nombreCompleto, empleado.getNombreCompleto());
        assertEquals(legajo, empleado.getLegajo());
        assertEquals(fechaIngreso, empleado.getFechaIngreso());
        assertEquals(puesto, empleado.getPuesto());
        assertEquals(salario, empleado.getSalario(), 0.001);

    }

    @Test
    public void crearEmpleadoConNombreEnBlancoException(){

        String nombreCompleto = "  ";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(CampoObligatorioException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConNombreNuloException(){

        String nombreCompleto = null;
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(CampoObligatorioException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }
    @Test
    public void crearEmpleadoConNombreVacioException(){

        String nombreCompleto = "";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(CampoObligatorioException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConLegajoEnBlancoException(){

        String nombreCompleto = "John Doe";
        String legajo = "  ";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(CampoObligatorioException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConLegajoNuloException(){

        String nombreCompleto = "John Doe";
        String legajo = null;
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(CampoObligatorioException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConLegajoVacioException(){

        String nombreCompleto = "John Doe";
        String legajo = "";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(CampoObligatorioException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConFechaIngresoNulaException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = null;
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(CampoObligatorioException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConFechaIngresoFuturaException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.now().plusDays(1);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(FechaIngresoFuturaException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConPuestoNuloException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = null;
        double salario = 600000;

        assertThrows(CampoObligatorioException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    //formatos de legajo
    @Test
    public void crearEmpleadoConLegajoFormatoValido(){

        String nombreCompleto = "John Doe";
        String legajo = "DEF1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        Empleado empleado = Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario);

        assertEquals(legajo, empleado.getLegajo());
    }


    @Test
    public void crearEmpleadoConLegajoFormatoInvalidoException(){

        String nombreCompleto = "John Doe";
        String legajo = "1234ABC"; // Formato inválido
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(FormatoLegajoInvalidoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConLegajoEnMinusculas(){

        String nombreCompleto = "John Doe";
        String legajo = "abc1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(FormatoLegajoInvalidoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }
    @Test
    public void crearEmpleadoConLegajoConCaracteresEspeciales(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC@123";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(FormatoLegajoInvalidoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }
    @Test
    public void crearEmpleadoConLegajoConEspacios(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC 1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(FormatoLegajoInvalidoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }
    @Test
    public void crearEmpleadoConLegajoConLongitudIncorrecta(){

        String nombreCompleto = "John Doe";
        String legajo = "AB123";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(FormatoLegajoInvalidoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConLegajoConMasDeCuatroNumerosException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC12345";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 600000;

        assertThrows(FormatoLegajoInvalidoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConSalarioNegativoException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = -50000;

        assertThrows(SalarioInvalidoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearEmpleadoConSalarioCeroException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 0;

        assertThrows(SalarioInvalidoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearAnalistaConSalarioInferiorAlPisoException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 499999;

        assertThrows(SalarioInferiorAlPisoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearAnalistaConSalarioIgualAlPisoEsValido(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.ANALISTA;
        double salario = 500000;

        Empleado empleado = Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario);

        assertEquals(salario, empleado.getSalario(), 0.001);
    }

    @Test
    public void crearSupervisorConSalarioInferiorAlPisoException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.SUPERVISOR;
        double salario = 799999;

        assertThrows(SalarioInferiorAlPisoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearSupervisorConSalarioIgualAlPisoEsValido(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.SUPERVISOR;
        double salario = 800000;

        Empleado empleado = Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario);

        assertEquals(salario, empleado.getSalario(), 0.001);
    }

    @Test
    public void crearGerenteConSalarioInferiorAlPisoException(){

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.GERENTE;
        double salario = 1199999;

        assertThrows(SalarioInferiorAlPisoException.class,
                () ->  Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario)
        );
    }

    @Test
    public void crearGerenteConSalarioIgualAlPisoEsValido() {

        String nombreCompleto = "John Doe";
        String legajo = "ABC1234";
        LocalDate fechaIngreso = LocalDate.of(2025, 5, 10);
        Puesto puesto = Puesto.GERENTE;
        double salario = 1200000;

        Empleado empleado = Empleado.crear(nombreCompleto, legajo, fechaIngreso, puesto, salario);

        assertEquals(salario, empleado.getSalario(), 0.001);
    }

    @Test
    public void reconstruirEmpleadoConservaElId() {

        UUID id = UUID.randomUUID();

        Empleado empleado = Empleado.reconstruir(
                id, "John Doe", "ABC1234", LocalDate.of(2020, 1, 1), Puesto.ANALISTA, 600000);

        assertEquals(id, empleado.getId());
        assertEquals("John Doe", empleado.getNombreCompleto());
        assertEquals("ABC1234", empleado.getLegajo());
        assertEquals(Puesto.ANALISTA, empleado.getPuesto());
        assertEquals(600000, empleado.getSalario(), 0.001);
    }
}
