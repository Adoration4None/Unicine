package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, String> {
    AdministradorTeatro findByEmailAndContrasena(String email, String contrasena);

    @Query("select a from AdministradorTeatro a where a.email = :email")
    AdministradorTeatro findByEmail(String email);
}
