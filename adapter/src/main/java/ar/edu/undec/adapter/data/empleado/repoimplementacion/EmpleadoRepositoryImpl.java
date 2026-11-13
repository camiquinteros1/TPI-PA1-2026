package ar.edu.undec.adapter.data.empleado.repoimplementacion;

import ar.edu.undec.adapter.data.empleado.crud.EmpleadoCrud;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    private final EmpleadoCrud crud;

    public EmpleadoRepositoryImpl(EmpleadoCrud crud) {
        this.crud = crud;
    }

    @Override
    public boolean existePorLegajo(String legajo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Empleado> buscarPorLegajo(String legajo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Empleado> listarTodos() {
        throw new UnsupportedOperationException();
    }
}