package ar.edu.undec.adapter.data.empleado.repoimplementacion;

import ar.edu.undec.adapter.data.empleado.crud.EmpleadoCrud;
import ar.edu.undec.adapter.data.empleado.mapper.EmpleadoDataMapper;
import ar.edu.undec.adapter.data.empleado.model.EmpleadoEntity;
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
        return crud.existsByLegajo(legajo);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        EmpleadoEntity guardada = crud.save(EmpleadoDataMapper.toEntity(empleado));
        return EmpleadoDataMapper.toDomain(guardada);
    }

    @Override
    public Optional<Empleado> buscarPorLegajo(String legajo) {
        return crud.findByLegajo(legajo).map(EmpleadoDataMapper::toDomain);
    }

    @Override
    public List<Empleado> listarTodos() {
        return crud.findAll().stream().map(EmpleadoDataMapper::toDomain).toList();
    }
}