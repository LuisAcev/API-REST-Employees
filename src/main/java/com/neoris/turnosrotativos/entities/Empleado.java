package com.neoris.turnosrotativos.entities;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity (name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "El campo Documento es obligatorio.")
    private Integer nroDocumento;
    @NotNull (message = "El campo Nombre es obligatorio.")
    @Pattern(regexp = "[a-zA-Z ]{2,254}" ,message = "Solo se permiten letras en el campo nombre.")
    private String nombre;
    @NotNull(message = "El campo Apellido es obligatorio")
    @Pattern(regexp = "[a-zA-Z ]{2,254}" ,message = "Solo se permiten letras en el campo apellido.")
    private String apellido;
    @NotNull(message = "El campo Email es obligatorio")
    @Email(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$" , message = "El Email ingresado no es correcto.")
    private String email;
    @NotNull(message = "El campo Fecha de Nacimiento es obligatorio.")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser posterior al día de la fecha.")
    private LocalDate fechaNacimiento; //  por ser una fecha se uso el formato localDate el cual recibe una estructura año-mes-dia.
    @NotNull(message = "El campo Fecha de Ingreso es obligatorio.")
    @PastOrPresent(message = "La fecha de ingreso no puede ser posterior al día de la fecha.")
    private LocalDate fechaIngreso;

    private LocalDate fechaCreacion ;

    @PreUpdate
    @PrePersist
    public void prePersist (){
        fechaCreacion = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Integer nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
