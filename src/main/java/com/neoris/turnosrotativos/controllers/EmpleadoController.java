package com.neoris.turnosrotativos.controllers;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.exceptions.BadRequestException;
import com.neoris.turnosrotativos.exceptions.BussinesException;
import com.neoris.turnosrotativos.exceptions.FoundException;
import com.neoris.turnosrotativos.repository.EmpleadoRepository;
import com.neoris.turnosrotativos.service.EmpleadoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
@CrossOrigin(origins = "http://localhost:4200/")

public class EmpleadoController {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoServices empleadoServices;


    @GetMapping("")

    public ResponseEntity<List<Empleado>> getEmpleados() {
        List<Empleado> empleadoList = empleadoServices.getEmpleado();
        return new ResponseEntity<>(empleadoList, HttpStatus.OK);

    }
    //Metodo para crear empelado
    @PostMapping("")
    public ResponseEntity<Empleado> addEmpleados(@Valid @RequestBody Empleado empleados) {

        LocalDate menorEdad = LocalDate.of(2006, 01, 1);

        if (empleados.getFechaNacimiento().isAfter( menorEdad ) ){  // Validacion de la mayoria de edad en este caso 18 años

            throw new BadRequestException("La edad del empleado no puede ser menor a 18 años.");

                 }
        if (empleadoRepository.existsByNroDocumento(empleados.getNroDocumento())) {

            throw new BussinesException("Ya existe un empleado con el documento ingresado.");
        }

        if (empleadoRepository.existsByEmail(empleados.getEmail())) { // VAlidacion si el emailExiste se utilizan los metodos implementados en el EmpeladoRepository

            throw new BussinesException("Ya existe un empleado con el email ingresado.");
        }

        Empleado empleadoAdded = empleadoRepository.save(empleados);
        return new ResponseEntity<>(empleadoAdded, HttpStatus.CREATED);
    }

    // Metodo para obtener  lista de empleados
    @GetMapping("/{empleadoId}")
    public ResponseEntity<Empleado> getEmpelado(@PathVariable("empleadoId") Integer id) {
        Optional<Empleado> empleadoList = empleadoRepository.findById(id);
        if (empleadoList.isPresent()) {
            return new ResponseEntity<>(empleadoList.get(), HttpStatus.OK);
        } else {
            throw new FoundException("No se encontro el empleado con id " + id);
        }

    }

    //Metodo para actualizar empleado
    @PutMapping("/{empleadoId}")
        public ResponseEntity<Empleado> putEmpelado(@PathVariable("empleadoId") Integer id, @RequestBody Empleado detallesEmpleado) {
        Optional<Empleado> empleadoList = empleadoRepository.findById(id);
                LocalDate fecha = LocalDate.now();
                LocalDate menorEdad = LocalDate.of(2006, 01, 1);
                detallesEmpleado.setId(id);
                // Comprobar la existencia del empleado por ID
                if(!empleadoList.isPresent() ) {
                    throw new FoundException("No se encontro el empleado con id: " + id);

                }
                // Comprobar si el empleado es menor de edad
                if (detallesEmpleado.getFechaNacimiento().isAfter( menorEdad ) ){  // Validacion de la mayoria de edad en este caso 18 años

                     throw new BadRequestException("La edad del empleado no puede ser menor a 18 años.");

                    }

               // Comprobar si la fecha de ingreso es posterior a la fecha actual
              if (detallesEmpleado.getFechaIngreso().isAfter( fecha ) ) {

                        throw new BadRequestException("La fecha de ingreso no puede ser posterior a la fecha actual.");
                    }

                else {


                   Empleado empleadoUpdate = empleadoRepository.save(detallesEmpleado);
                   return ResponseEntity.ok(empleadoUpdate);
                }


        }

        //Metodo para borrar empelado
    @DeleteMapping("/{empleadoId}")
    public ResponseEntity<Empleado> delet(@PathVariable("empleadoId") Integer id) {
        Optional<Empleado> empleadoList = empleadoRepository.findById(id);

        if (!empleadoList.isPresent()) {

            throw new FoundException("No se encontró el empleado con id: " + id);
        } else {
            empleadoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


}
