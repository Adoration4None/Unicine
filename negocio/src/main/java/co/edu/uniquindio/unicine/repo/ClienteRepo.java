package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.EstadoPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {

    @Query("select c from Cliente c where c.email = ?1")
    Cliente obtener(String email);

    @Query("select c from Cliente c where c.email = :email")
    Cliente findByEmail(String email);

    Cliente findByEmailAndContrasena(String email, String contrasena);

    @Query("select cup from Cliente cli, in (cli.cuponesCliente) cup where cli.email  = :email")
    List<Cupon> obtenerCupones(String email);

    @Query("select comp from Compra comp where comp.cliente.cedula = :cedulaCliente")
    List<Compra> obtenerCompras(String cedulaCliente);

    @Query("select c.estado from Cliente c where c.cedula = :cedulaCliente")
    EstadoPersona obtenerEstadoCliente(String cedulaCliente);

}
