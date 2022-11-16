package com.gestion.empleados.app.Controller;

import com.gestion.empleados.app.Exception.ResourceNotFoundException;
import com.gestion.empleados.app.Model.Empleado;
import com.gestion.empleados.app.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository repository;

    //listar empleado
    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados(){
        return repository.findAll();
    }

    //guardar empleado
    @PostMapping("/empleados")
    public Empleado saveEmpleado(@RequestBody Empleado empleado){
        return repository.save(empleado);
    }

    //buscar empleado
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorID(@PathVariable long id){
        Empleado empleado = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con ID: " + id));
        return ResponseEntity.ok(empleado);
    }

    //actualizar empleado
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable long id, @RequestBody Empleado empleado){
        Empleado updateEmpleado = repository.findById(id).get();
        updateEmpleado.setNombre(empleado.getNombre());
        updateEmpleado.setApellido(empleado.getApellido());
        updateEmpleado.setEmail(empleado.getEmail());
        repository.save(updateEmpleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable long id){
        Empleado deleteEmpleado = repository.findById(id).get();
        repository.delete(deleteEmpleado);
        return ResponseEntity.ok(deleteEmpleado);
    }

}
