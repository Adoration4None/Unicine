package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeatroRepo extends JpaRepository<Teatro, Integer> {

    @Query("select t from Teatro t where t.ciudad.nombre = :nombreCiudad")
    public List<Teatro> obtenerTeatrosCiudad(String nombreCiudad);
}
