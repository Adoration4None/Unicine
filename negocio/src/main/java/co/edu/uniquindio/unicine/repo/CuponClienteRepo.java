package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.entidades.EstadoCupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponClienteRepo extends JpaRepository<CuponCliente, Integer> {

    @Query("select c from CuponCliente c where c.cliente.cedula = :cedula")
    List<CuponCliente> obtenerCuponesCliente(String cedula);

    @Query("select c from CuponCliente c where c.cliente.cedula = :cedula and c.estado = :estado")
    List<CuponCliente> obtenerCuponesClienteEstado(String cedula, EstadoCupon estado);

    CuponCliente findByClienteAndCupon(Cliente clienteEncontrado, Cupon cuponEncontrado);
}
