package co.edu.uniquindio.unicine.rest;

import co.edu.uniquindio.unicine.dto.Mensaje;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ciudades")
public class CiudadRestController {

    @Autowired
    private AdministradorServicio administradorServicio;

    @GetMapping
    public List<Ciudad> listarCiudades() {
        return administradorServicio.listarCiudades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCiudad(@PathVariable("id") String idCiudad) {
        try {
            Ciudad ciudad = administradorServicio.obtenerCiudad( Integer.valueOf(idCiudad) );
            return ResponseEntity.status(200).body(ciudad);
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> crearCiudad(@RequestBody Ciudad ciudad) {
        try {
            administradorServicio.crearCiudad(ciudad);
            return ResponseEntity.status(201).body( new Mensaje("Ciudad creada correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizarCiudad(@RequestBody Ciudad ciudad) {
        try{
            administradorServicio.actualizarCiudad(ciudad);
            return ResponseEntity.status(200).body( new Mensaje("Ciudad actualizada correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminarCiudad(@PathVariable("id") String idCiudad) {
        try{
            administradorServicio.eliminarCiudad( Integer.valueOf(idCiudad) );
            return ResponseEntity.status(200).body( new Mensaje("Ciudad eliminada correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

}
