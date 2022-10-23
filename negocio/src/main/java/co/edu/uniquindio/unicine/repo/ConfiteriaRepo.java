package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria, Integer> {

    @Query("select c.unidades from Confiteria c where c.id = :idConfiteria")
    Integer obtenerUnidadesDisponibles(Integer idConfiteria);

    Confiteria findByNombre(String nombreConfiteria);
}
