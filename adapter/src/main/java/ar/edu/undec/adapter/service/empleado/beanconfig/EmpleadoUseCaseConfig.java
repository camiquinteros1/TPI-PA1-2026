
package ar.edu.undec.adapter.service.empleado.beanconfig;

import empleado.input.CrearEmpleadoUseCase;
import empleado.input.BuscarEmpleadoPorLegajoUseCase;
import empleado.input.ListarEmpleadosUseCase;
import empleado.output.EmpleadoRepository;
import empleado.usecase.CrearEmpleadoService;
import empleado.usecase.BuscarEmpleadoPorLegajoService;
import empleado.usecase.ListarEmpleadosService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmpleadoUseCaseConfig {

    @Bean
    public CrearEmpleadoUseCase crearEmpleadoUseCase(EmpleadoRepository repositorio) {
        return new CrearEmpleadoService(repositorio);
    }

    @Bean
    public BuscarEmpleadoPorLegajoUseCase buscarEmpleadoPorLegajoUseCase(
            EmpleadoRepository repositorio) {
        return new BuscarEmpleadoPorLegajoService(repositorio);
    }

    @Bean
    public ListarEmpleadosUseCase listarEmpleadosUseCase(EmpleadoRepository repositorio) {
        return new ListarEmpleadosService(repositorio);
    }
}
