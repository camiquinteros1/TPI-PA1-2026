package empleado.usecase;

import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;

public class BuscarEmpleadoPorLegajoService {

    private final EmpleadoRepository repositorio;

    public BuscarEmpleadoPorLegajoService(EmpleadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Empleado buscarPorLegajo(String legajo) {
        throw new UnsupportedOperationException();
    }

}
