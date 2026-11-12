package empleado.usecase;

import empleado.exception.LegajoDuplicadoException;
import empleado.input.CrearEmpleadoCommand;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;

public class CrearEmpleadoService {

    private final EmpleadoRepository repositorio;

    public CrearEmpleadoService(EmpleadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Empleado crear(CrearEmpleadoCommand comando) {

        Empleado empleado = Empleado.crear(
                comando.getNombreCompleto(),
                comando.getLegajo(),
                comando.getFechaIngreso(),
                comando.getPuesto(),
                comando.getSalario()
        );

        if(repositorio.existePorLegajo(comando.getLegajo())) {
            throw new LegajoDuplicadoException("El legajo ya existe: " + comando.getLegajo());
        }

        return repositorio.guardar(empleado);
    }
}
