package empleado.usecase;

import empleado.exception.EmpleadoNoEncontradoException;
import empleado.output.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
