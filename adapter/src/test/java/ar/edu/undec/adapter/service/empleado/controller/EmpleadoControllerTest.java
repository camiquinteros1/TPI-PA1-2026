
package ar.edu.undec.adapter.service.empleado.controller;

import empleado.input.CrearEmpleadoUseCase;
import empleado.input.BuscarEmpleadoPorLegajoUseCase;
import empleado.input.ListarEmpleadosUseCase;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.exception.EmpleadoNoEncontradoException;
import empleado.exception.LegajoDuplicadoException;
import empleado.exception.SalarioInvalidoException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrearEmpleadoUseCase crearEmpleadoUseCase;

    @MockBean
    private BuscarEmpleadoPorLegajoUseCase buscarEmpleadoPorLegajoUseCase;

    @MockBean
    private ListarEmpleadosUseCase listarEmpleadosUseCase;

    @Test
    void deberiaCrearEmpleadoYRetornar201() throws Exception {

        Empleado empleado = Empleado.crear(
                "Juan Perez",
                "ABC1234",
                LocalDate.of(2025, 5, 10),
                Puesto.ANALISTA,
                500000
        );

        when(crearEmpleadoUseCase.crear(any()))
                .thenReturn(empleado);

        mockMvc.perform(post("/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "nombreCompleto": "Juan Perez",
                            "legajo": "ABC1234",
                            "fechaIngreso": "2025-05-10",
                            "puesto": "ANALISTA",
                            "salario": 500000
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombreCompleto").value("Juan Perez"))
                .andExpect(jsonPath("$.legajo").value("ABC1234"))
                .andExpect(jsonPath("$.puesto").value("ANALISTA"))
                .andExpect(jsonPath("$.salario").value(500000));
    }

    @Test
    void deberiaBuscarEmpleadoYRetornar200() throws Exception {

        Empleado empleado = Empleado.crear(
                "Juan Perez",
                "ABC1234",
                LocalDate.of(2025, 5, 10),
                Puesto.ANALISTA,
                500000
        );

        when(buscarEmpleadoPorLegajoUseCase.buscarPorLegajo("ABC1234"))
                .thenReturn(empleado);

        mockMvc.perform(get("/empleados/ABC1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreCompleto").value("Juan Perez"))
                .andExpect(jsonPath("$.legajo").value("ABC1234"));
    }

    @Test
    void deberiaRetornar404CuandoNoExisteElEmpleado() throws Exception {

        when(buscarEmpleadoPorLegajoUseCase.buscarPorLegajo("XYZ9999"))
                .thenThrow(new EmpleadoNoEncontradoException("Empleado no encontrado"));

        mockMvc.perform(get("/empleados/XYZ9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void deberiaListarEmpleadosYRetornar200() throws Exception {

        Empleado empleado = Empleado.crear(
                "Juan Perez",
                "ABC1234",
                LocalDate.of(2025, 5, 10),
                Puesto.ANALISTA,
                500000
        );

        when(listarEmpleadosUseCase.listar())
                .thenReturn(List.of(empleado));

        mockMvc.perform(get("/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreCompleto").value("Juan Perez"))
                .andExpect(jsonPath("$[0].legajo").value("ABC1234"));
    }

    @Test
    void deberiaRetornar409CuandoElLegajoEstaDuplicado() throws Exception {

        when(crearEmpleadoUseCase.crear(any()))
                .thenThrow(new LegajoDuplicadoException("El legajo ya existe"));

        mockMvc.perform(post("/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "nombreCompleto": "Juan Perez",
                            "legajo": "ABC1234",
                            "fechaIngreso": "2025-05-10",
                            "puesto": "ANALISTA",
                            "salario": 500000
                        }
                        """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void deberiaRetornar400CuandoElSalarioEsInvalido() throws Exception {

        when(crearEmpleadoUseCase.crear(any()))
                .thenThrow(new SalarioInvalidoException("El salario debe ser mayor a cero"));

        mockMvc.perform(post("/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "nombreCompleto": "Juan Perez",
                            "legajo": "ABC1234",
                            "fechaIngreso": "2025-05-10",
                            "puesto": "ANALISTA",
                            "salario": 0
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}
