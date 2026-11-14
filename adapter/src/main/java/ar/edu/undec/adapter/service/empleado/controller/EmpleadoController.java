package ar.edu.undec.adapter.service.empleado.controller;

import ar.edu.undec.adapter.service.empleado.mapper.EmpleadoWebMapper;
import ar.edu.undec.adapter.service.empleado.model.CrearEmpleadoRequest;
import ar.edu.undec.adapter.service.empleado.model.EmpleadoResponse;

import empleado.input.CrearEmpleadoUseCase;
import empleado.input.BuscarEmpleadoPorLegajoUseCase;
import empleado.input.ListarEmpleadosUseCase;
import empleado.input.CrearEmpleadoCommand;

import empleado.modelo.Empleado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final CrearEmpleadoUseCase crearEmpleadoUseCase;
    private final BuscarEmpleadoPorLegajoUseCase buscarEmpleadoPorLegajoUseCase;
    private final ListarEmpleadosUseCase listarEmpleadosUseCase;

    public EmpleadoController(
            CrearEmpleadoUseCase crearEmpleadoUseCase,
            BuscarEmpleadoPorLegajoUseCase buscarEmpleadoPorLegajoUseCase,
            ListarEmpleadosUseCase listarEmpleadosUseCase) {

        this.crearEmpleadoUseCase = crearEmpleadoUseCase;
        this.buscarEmpleadoPorLegajoUseCase = buscarEmpleadoPorLegajoUseCase;
        this.listarEmpleadosUseCase = listarEmpleadosUseCase;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> crear(
            @RequestBody CrearEmpleadoRequest request) {

        CrearEmpleadoCommand comando = EmpleadoWebMapper.toCommand(request);

        Empleado empleado = crearEmpleadoUseCase.crear(comando);

        EmpleadoResponse response = EmpleadoWebMapper.toResponse(empleado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<EmpleadoResponse> buscarPorLegajo(
            @PathVariable("legajo") String legajo) {

        Empleado empleado = buscarEmpleadoPorLegajoUseCase.buscarPorLegajo(legajo);

        return ResponseEntity.ok(EmpleadoWebMapper.toResponse(empleado));
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> listar() {

        List<EmpleadoResponse> empleados = listarEmpleadosUseCase.listar()
                .stream()
                .map(EmpleadoWebMapper::toResponse)
                .toList();

        return ResponseEntity.ok(empleados);
    }
}