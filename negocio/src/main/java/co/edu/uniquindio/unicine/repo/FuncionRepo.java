package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombre from Funcion f where f.id = :idFuncion")
    String obtenerNombrePelicula(Integer idFuncion);

    @Query("select f.pelicula from Funcion f where LOWER(f.pelicula.nombre) like concat('%', :busqueda, '%') and f.sala.teatro.ciudad.id = :idCiudad and f.pelicula.estado <> 'NO_DISPONIBLE'")
    List<Pelicula> buscarPeliculas(String busqueda, Integer idCiudad);

    Funcion findByPeliculaAndSalaAndHorario(Pelicula pelicula, Sala sala, Horario horario);

    @Query("select f from Funcion f where f.sala.id = :idSala and f.horario.id = :idHorario")
    List<Funcion> obtenerFuncionesSalaHorario(Integer idSala, Integer idHorario);

    @Query("select distinct f from Funcion f where f.pelicula.id = :idPelicula and f.pelicula.estado <> 'NO_DISPONIBLE'")
    List<Funcion> obtenerFuncionesPelicula(Integer idPelicula);
}
