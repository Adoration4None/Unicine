package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {

    // Consulta manual
    @Query("select c from Cliente c where c.email = ?1")
    Cliente obtener(String email);

    // Consulta por inferencia
    Cliente findByEmail(String email);

    Cliente findByEmailAndContrasena(String email, String contrasena);
}
