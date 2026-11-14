package empleado.input;

import empleado.modelo.Empleado;

public interface CrearEmpleadoUseCase {
    Empleado crear (CrearEmpleadoCommand comando);
}
