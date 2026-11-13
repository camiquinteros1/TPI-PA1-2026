package ar.edu.undec.adapter.data.empleado;

import ar.edu.undec.adapter.data.empleado.repoimplementacion.EmpleadoRepositoryImpl;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(EmpleadoRepositoryImpl.class)
public class EmpleadoRepositoryImplTest {

    @Autowired
    private EmpleadoRepositoryImpl repositorio;

    private Empleado nuevoEmpleado(String legajo) {
        return Empleado.crear("John Doe", legajo, LocalDate.of(2020, 1, 1), Puesto.ANALISTA, 600000);
    }

    @Test
    public void guardarYBuscarPorLegajoRecuperaElEmpleado() {
        Empleado guardado = repositorio.guardar(nuevoEmpleado("ABC1234"));

        Optional<Empleado> encontrado = repositorio.buscarPorLegajo("ABC1234");

        assertTrue(encontrado.isPresent());
        assertEquals(guardado.getId(), encontrado.get().getId());
        assertEquals("John Doe", encontrado.get().getNombreCompleto());
        assertEquals("ABC1234", encontrado.get().getLegajo());
        assertEquals(LocalDate.of(2020, 1, 1), encontrado.get().getFechaIngreso());
        assertEquals(Puesto.ANALISTA, encontrado.get().getPuesto());
        assertEquals(600000, encontrado.get().getSalario(), 0.001);
    }

    @Test
    public void buscarPorLegajoInexistenteRetornaVacio() {
        assertTrue(repositorio.buscarPorLegajo("ZZZ9999").isEmpty());
    }

    @Test
    public void existePorLegajoRetornaTrueSiExiste() {
        repositorio.guardar(nuevoEmpleado("ABC1234"));

        assertTrue(repositorio.existePorLegajo("ABC1234"));
    }

    @Test
    public void existePorLegajoRetornaFalseSiNoExiste() {
        assertFalse(repositorio.existePorLegajo("ZZZ9999"));
    }

    @Test
    public void listarTodosRetornaLosEmpleadosGuardados() {
        repositorio.guardar(nuevoEmpleado("ABC1234"));
        repositorio.guardar(nuevoEmpleado("DEF5678"));

        List<Empleado> empleados = repositorio.listarTodos();

        assertEquals(2, empleados.size());
    }
}