package empleado.usecase;

import empleado.input.BuscarEmpleadoPorLegajoUseCase;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;
import empleado.exception.*;

public class BuscarEmpleadoPorLegajoService implements BuscarEmpleadoPorLegajoUseCase {

    private final EmpleadoRepository repositorio;

    public BuscarEmpleadoPorLegajoService(EmpleadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Empleado buscarPorLegajo(String legajo) {

        return repositorio.buscarPorLegajo(legajo)
                .orElseThrow(() -> new EmpleadoNoEncontradoException(
                        "No se encontró un empleado con el legajo " + legajo));

    }

}
