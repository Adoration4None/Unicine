package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Pelicula;

import java.util.List;

public interface ClienteServicio {

    Cliente iniciarSesion(String email, String contrasena) throws Exception;

    Cliente registrar(Cliente cliente) throws Exception;

    Cliente actualizar(Cliente cliente) throws Exception;

    void eliminar(String cedulaCliente) throws Exception;

    Cliente obtener(String cedulaCliente) throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarCompras(String cedulaCliente) throws Exception;

    Compra redimirCupon(Integer idCupon, Compra compra) throws Exception;

    List<Pelicula> buscarPeliculas(String busqueda) throws Exception;

    Compra realizarCompra(Compra compra, Cliente cliente) throws Exception;

    boolean cambiarContrasena(String emailCliente) throws Exception;

    Cupon agregarCupon(String nombreCupon, Cliente cliente) throws Exception;
}
