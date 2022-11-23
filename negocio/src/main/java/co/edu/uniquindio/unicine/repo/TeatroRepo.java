package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeatroRepo extends JpaRepository<Teatro, Integer> {

    public Teatro findByNombreAndDireccionAndCiudad(String nombreTeatro, String direccionTeatro, Ciudad ciudadTeatro);

    @Query("select distinct f.sala.teatro from Funcion f where f.sala.teatro.ciudad.id = :idCiudad")
    List<Teatro> obtenerTeatrosCiudad(Integer idCiudad);

    @Query("select distinct f.sala.teatro from Funcion f where f.sala.teatro.ciudad.id = :idCiudad and f.pelicula.id = :idPelicula and f.pelicula.estado <> 'NO_DISPONIBLE'")
    List<Teatro> obtenerTeatrosPelicula(Integer idCiudad, Integer idPelicula);

    @Query("select c.teatros from Ciudad c where c.id = :idCiudad")
    List<Teatro> obtenerTeatrosCiudadAdmin(Integer idCiudad);
}
