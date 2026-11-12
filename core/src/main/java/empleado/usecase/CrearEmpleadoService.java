package empleado.usecase;

import empleado.input.CrearEmpleadoCommand;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;

public class CrearEmpleadoService {

    private final EmpleadoRepository repositorio;

    public CrearEmpleadoService(EmpleadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Empleado crear(CrearEmpleadoCommand comando) {
        throw new UnsupportedOperationException();
    }
}
