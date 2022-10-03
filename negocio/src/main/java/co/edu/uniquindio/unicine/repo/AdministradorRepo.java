package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepo extends JpaRepository<AdministradorTeatro, String> {
}
