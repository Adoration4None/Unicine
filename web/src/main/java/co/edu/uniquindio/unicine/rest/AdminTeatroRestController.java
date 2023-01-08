package co.edu.uniquindio.unicine.rest;

import co.edu.uniquindio.unicine.dto.Mensaje;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/administradores")
public class AdminTeatroRestController {

    @Autowired
    private AdministradorServicio administradorServicio;


    @GetMapping
    public List<AdministradorTeatro> listarAdministradores() {
        return administradorServicio.listarAdministradores();
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> obtenerAdministrador(@PathVariable("cedula") String cedula) {
        try {
            AdministradorTeatro adminTeatro = administradorServicio.obtenerAdministrador(cedula);
            return ResponseEntity.status(200).body(adminTeatro);
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> crearAdministrador(@RequestBody AdministradorTeatro adminTeatro) {
        try {
            administradorServicio.crearAdministrador(adminTeatro);
            return ResponseEntity.status(201).body( new Mensaje("Administrador de teatro creado correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizarAdministrador(@RequestBody AdministradorTeatro adminTeatro) {
        try{
            administradorServicio.actualizarAdministrador(adminTeatro);
            return ResponseEntity.status(200).body( new Mensaje("Administrador de teatro actualizado correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Mensaje> eliminarAdministrador(@PathVariable("cedula") String cedula) {
        try{
            administradorServicio.eliminarAdministrador(cedula);
            return ResponseEntity.status(200).body( new Mensaje("Administrador de teatro eliminado correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }
}
