package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepo extends JpaRepository<Sala, Integer> {

    @Query("select f.pelicula from Sala s, in (s.funciones) f where s.id = :idSala ")
    List<Pelicula> obtenerPeliculasSala(Integer idSala);
}
