package empleado.usecase;

import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import empleado.input.CrearEmpleadoCommand;
import empleado.output.EmpleadoRepository;
import empleado.exception.LegajoDuplicadoException;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CrearEmpleadoServiceTest {

    private EmpleadoRepository repositorio;

    private CrearEmpleadoService servicio;

    private final String legajo = "ABC1234";

    @BeforeEach
    public void setUp() {
        repositorio = mock(EmpleadoRepository.class);
        servicio = new CrearEmpleadoService(repositorio);
    }

    @Test
    public void crearEmpleadoValido(){

        CrearEmpleadoCommand comando = new CrearEmpleadoCommand(
                "John Doe", legajo, LocalDate.of(2020, 1, 1), Puesto.ANALISTA, 600000);

        when (repositorio.existePorLegajo(legajo)).thenReturn(false);
        when (repositorio.guardar(any(Empleado.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Empleado empleado = servicio.crear(comando);

        assertNotNull(empleado);
        assertEquals("John Doe", empleado.getNombreCompleto());
        assertEquals(legajo, empleado.getLegajo());
        assertEquals(Puesto.ANALISTA, empleado.getPuesto());

        verify(repositorio).existePorLegajo(legajo);
        verify(repositorio).guardar(same(empleado));

    }
    @Test
    public void crearEmpleadoConLegajoExistente() {
        CrearEmpleadoCommand comando = new CrearEmpleadoCommand(
                "John Doe", legajo, LocalDate.of(2021, 5, 15), Puesto.ANALISTA, 600000);

        when(repositorio.existePorLegajo(legajo)).thenReturn(true);

        assertThrows(LegajoDuplicadoException.class, () -> {
            servicio.crear(comando);
        });

        verify(repositorio).existePorLegajo(legajo);
        verify(repositorio, never()).guardar(any(Empleado.class));
    }
}
