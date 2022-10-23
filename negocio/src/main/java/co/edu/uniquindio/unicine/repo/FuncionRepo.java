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

    @Query("select f.sala.entradas from Funcion f where f.id = :idFuncion")
    List<Entrada> obtenerEntradasCompradasFuncion(Integer idFuncion);

    Funcion findByPeliculaAndSalaAndHorario(Pelicula pelicula, Sala sala, Horario horario);
}
