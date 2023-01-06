package co.edu.uniquindio.unicine.rest;

import co.edu.uniquindio.unicine.dto.Mensaje;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/peliculas")
public class PeliculaRestController {

    @Autowired
    private AdministradorServicio administradorServicio;

    @GetMapping
    public List<Pelicula> listarPeliculas() {
        return administradorServicio.listarPeliculas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPelicula(@PathVariable("id") String idPelicula) {
        try{
            Pelicula pelicula = administradorServicio.obtenerPelicula( Integer.valueOf(idPelicula) );
            return ResponseEntity.status(200).body(pelicula);
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }

    }

    @PostMapping
    public ResponseEntity<Mensaje> crearPelicula(@RequestBody Pelicula pelicula) {
        try{
            administradorServicio.crearPelicula(pelicula);
            return ResponseEntity.status(201).body( new Mensaje("Pelicula creada correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }

    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizarPelicula(@RequestBody Pelicula pelicula) {
        try{
            administradorServicio.actualizarPelicula(pelicula);
            return ResponseEntity.status(200).body( new Mensaje("Pelicula actualizada correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminarPelicula(@PathVariable("id") String idPelicula) {
        try{
            administradorServicio.eliminarPelicula( Integer.valueOf(idPelicula) );
            return ResponseEntity.status(200).body( new Mensaje("Pelicula eliminada correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }
}
