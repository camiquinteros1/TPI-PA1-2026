package empleado.usecase;

import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;
import empleado.exception.*;

public class BuscarEmpleadoPorLegajoService {

    private final EmpleadoRepository repositorio;

    public BuscarEmpleadoPorLegajoService(EmpleadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Empleado buscarPorLegajo(String legajo) {

        return repositorio.buscarPorLegajo(legajo)
                .orElseThrow(() -> new EmpleadoNoEncontradoException(
                        "No se encontró un empleado con el legajo " + legajo));

    }

}
