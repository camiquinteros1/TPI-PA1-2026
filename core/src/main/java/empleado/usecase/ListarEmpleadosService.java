package empleado.usecase;

import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;

import java.util.List;

public class ListarEmpleadosService {

    private EmpleadoRepository repositorio;

    public ListarEmpleadosService(EmpleadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Empleado> listar() {
        return repositorio.listarTodos();
    }

}
