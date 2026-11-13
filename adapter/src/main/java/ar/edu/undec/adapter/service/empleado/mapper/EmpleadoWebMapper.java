package ar.edu.undec.adapter.service.empleado.mapper;

import ar.edu.undec.adapter.service.empleado.model.CrearEmpleadoRequest;
import ar.edu.undec.adapter.service.empleado.model.EmpleadoResponse;
import empleado.input.CrearEmpleadoCommand;
import empleado.modelo.Empleado;

public class EmpleadoWebMapper {

    private EmpleadoWebMapper() {
    }

    public static CrearEmpleadoCommand toCommand(CrearEmpleadoRequest request) {
        return new CrearEmpleadoCommand(
                request.getNombreCompleto(),
                request.getLegajo(),
                request.getFechaIngreso(),
                request.getPuesto(),
                request.getSalario()
        );
    }

    public static EmpleadoResponse toResponse(Empleado empleado) {
        return new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombreCompleto(),
                empleado.getLegajo(),
                empleado.getFechaIngreso(),
                empleado.getPuesto(),
                empleado.getSalario()
        );
    }
}