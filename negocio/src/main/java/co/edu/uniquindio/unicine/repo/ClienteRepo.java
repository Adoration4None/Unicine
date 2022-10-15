package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {

    @Query( "select c.nombre, c.urlfoto from Cliente c where correo = ?1")
    Cliente obtener(String email);

    Cliente 


}
