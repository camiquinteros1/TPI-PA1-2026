package empleado.usecase;

import empleado.input.ListarEmpleadosUseCase;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;

import java.util.List;

public class ListarEmpleadosService implements ListarEmpleadosUseCase {

    private EmpleadoRepository repositorio;

    public ListarEmpleadosService(EmpleadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Empleado> listar() {
        return repositorio.listarTodos();
    }

}
