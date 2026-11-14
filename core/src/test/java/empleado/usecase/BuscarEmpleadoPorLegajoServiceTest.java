package empleado.usecase;

import empleado.exception.EmpleadoNoEncontradoException;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BuscarEmpleadoPorLegajoServiceTest {

    private EmpleadoRepository repositorio;
    private BuscarEmpleadoPorLegajoService servicio;

    private final String legajo = "ABC1234";


    @BeforeEach
    public void setUp() {
        repositorio = mock(EmpleadoRepository.class);
        servicio = new BuscarEmpleadoPorLegajoService(repositorio);
    }

    @Test
    public void buscarEmpleadoPorLegajoInexistente() {

        when(repositorio.buscarPorLegajo(legajo)).thenReturn(Optional.empty());
        assertThrows(EmpleadoNoEncontradoException.class, () -> {
            servicio.buscarPorLegajo(legajo);
        });

        verify(repositorio).buscarPorLegajo(legajo);

    }

    @Test
    public void buscarEmpleadoPorLegajoExistente() {

        Empleado empleado = Empleado.crear("John Doe", legajo, LocalDate.of(2020, 1, 1), Puesto.ANALISTA, 600000);

        when(repositorio.buscarPorLegajo(legajo)).thenReturn(Optional.of(empleado));

        Empleado resultado = servicio.buscarPorLegajo(legajo);
        assertSame(empleado, resultado);

        verify(repositorio).buscarPorLegajo(legajo);
    }


}
