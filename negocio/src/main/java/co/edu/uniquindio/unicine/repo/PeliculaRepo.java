package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, String> {

    @Query("select new co.edu.uniquindio.unicine.dto.FuncionDTO(f.pelicula.nombre, f.pelicula.estado, f.pelicula.imagen, f.sala.id, f.sala.teatro.direccion, f.sala.teatro.ciudad.nombre, f.horario) from Funcion f where f.pelicula.nombre = :nombrePelicula")
    List<FuncionDTO> obtenerFunciones(String nombrePelicula);
    @Query("select p from Pelicula p where LOWER(p.nombre) like concat('%', :busqueda, '%') ")
    List<Pelicula> buscarPeliculas(String busqueda);
}
