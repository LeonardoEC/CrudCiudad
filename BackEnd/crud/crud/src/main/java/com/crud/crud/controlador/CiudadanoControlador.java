package com.crud.crud.controlador;

import com.crud.crud.excepciones.ResourceNotFoundException;
import com.crud.crud.modelo.Ciudadano;
import com.crud.crud.repositorio.CiudadanoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
@CrossOrigin
public class CiudadanoControlador {

    @Autowired
    private CiudadanoRepositorio repositorio;

    @GetMapping("/ciudadanos")
    private List<Ciudadano> ListarTodosLosCiudadanos(){
        return repositorio.findAll();
    }

    @GetMapping("/ciudadanos/{id}")
    public ResponseEntity<Ciudadano> obtenerCiudadanoPorId(@PathVariable Long id){
        Ciudadano ciudadano = repositorio.findById(id) .orElseThrow(()-> new ResourceNotFoundException("No existe el ciudadano con el ID: " + id));
        return ResponseEntity.ok(ciudadano);
    }

    @PostMapping("/ciudadanos")
    public Ciudadano guardarCiudadano(@RequestBody Ciudadano ciudadano){
        return repositorio.save(ciudadano);
    }

    @PutMapping("/ciudadanos/{id}")
    public ResponseEntity<Ciudadano> actualizarCiudadano(@PathVariable Long id, @RequestBody Ciudadano detallesCiudadano){
        Ciudadano ciudadano = repositorio.findById(id) .orElseThrow(()-> new ResourceNotFoundException("No existe el ciudadano con el ID: " + id));

        ciudadano.setNombre(detallesCiudadano.getNombre());
        ciudadano.setApellido(detallesCiudadano.getApellido());
        ciudadano.setTelefono(detallesCiudadano.getTelefono());

        Ciudadano ciudadanoActualizado = repositorio.save(ciudadano);
        return ResponseEntity.ok(ciudadanoActualizado);
    }

    @DeleteMapping("/ciudadanos/{id}")
    public ResponseEntity<Map<String,Boolean>>eliminarCiudadano(@PathVariable Long id){
        Ciudadano ciudadano = repositorio.findById(id) .orElseThrow(()-> new ResourceNotFoundException("No existe el ciudadano con el ID: " + id));

        repositorio.delete(ciudadano);
        Map<String,Boolean> respuesta = new HashMap<>();
        return ResponseEntity.ok(respuesta);
    }
}
