package empleado.usecase;

import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListarEmpleadosServiceTest {
    private EmpleadoRepository repositorio;
    private ListarEmpleadosService servicio;

    @BeforeEach
    public void setUp() {
        repositorio = mock(EmpleadoRepository.class);
        servicio = new ListarEmpleadosService(repositorio);
    }

    @Test
    public void listarEmpleados() {
        Empleado uno = Empleado.crear("John Doe", "ABC1234", LocalDate.of(2020, 1, 1), Puesto.ANALISTA, 600000);
        Empleado dos = Empleado.crear("Jane Smith", "DEF5678", LocalDate.of(2021, 5, 15), Puesto.GERENTE, 1500000);

        when(repositorio.listarTodos()).thenReturn(List.of(uno, dos));

        List<Empleado> resultado = servicio.listar();
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(uno));
        assertTrue(resultado.contains(dos));

        verify(repositorio).listarTodos();
    }

    @Test
    public void listarEmpleadosVacio() {
        when(repositorio.listarTodos()).thenReturn(List.of());

        List<Empleado> resultado = servicio.listar();

        assertTrue(resultado.isEmpty());
        verify(repositorio).listarTodos();
    }

}
