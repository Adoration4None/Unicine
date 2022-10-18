package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {


    //@Query( "select c.nombre, c.urlfoto from Cliente c where correo = ?1")
    // Consulta manual
    @Query("select c from Cliente c where c.email = ?1")
    Cliente obtener(String email);

    // Consulta por inferencia
    @Query("select c from Cliente c where c.email = :email")
    Cliente findByEmail(String email);

    //@Query("select c from Cliente c where c.email and c.contrasena = :email and :contrasena")
    Cliente findByEmailAndContrasena(String email, String contrasena);

    @Query("select cup from Cliente cli, in (cli.cupones) cup where cli.email  = :email")
    List<Cupon> obtenerCupones(String email);


    //@Query("select c.compras from Cliente c where c.email = :email")
    @Query("select comp from Cliente c, in (c.compras) comp where c.cedula = :cedulaCliente")
    List<Compra> obtenerCompras(String cedulaCliente);

    //@Query("select c, case when (count(c.contrasena) > 0) then true else false end from Cliente c where c.contrasena and c.cedula = :contrasena and :cedulaCliente")
    List<Object[]> validarContrasena(String contrasena, String cedulaCliente);

    //@Query("select case when (count(cup) > 0) then true else false end from Cupon cup where cup.id = :id")
}
