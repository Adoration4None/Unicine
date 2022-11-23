package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer> {

    @Query("select f.pelicula from Funcion f where f.sala.teatro.id = :idTeatro and f.sala.teatro.ciudad.id = :idCiudad and f.pelicula.estado <> 'NO_DISPONIBLE'")
    List<Pelicula> obtenerPeliculasTeatroCiudad(Integer idTeatro, Integer idCiudad);
}
