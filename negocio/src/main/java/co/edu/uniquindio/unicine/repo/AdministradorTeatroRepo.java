package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, String> {
    AdministradorTeatro findByEmailAndContrasena(String email, String contrasena);
}
