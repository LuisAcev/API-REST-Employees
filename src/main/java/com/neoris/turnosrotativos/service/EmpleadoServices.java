package com.neoris.turnosrotativos.service;
import com.neoris.turnosrotativos.entities.Empleado;
import java.util.List;


public interface EmpleadoServices {

    List<Empleado> getEmpleado ();


    Empleado addEmpleado(Empleado empelado);


}
