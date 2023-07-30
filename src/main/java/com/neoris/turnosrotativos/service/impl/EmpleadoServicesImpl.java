package com.neoris.turnosrotativos.service.impl;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.repository.EmpleadoRepository;
import com.neoris.turnosrotativos.service.EmpleadoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoServicesImpl implements EmpleadoServices {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> getEmpleado() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public Empleado addEmpleado(Empleado empleado) {
        return null;
    }




}
