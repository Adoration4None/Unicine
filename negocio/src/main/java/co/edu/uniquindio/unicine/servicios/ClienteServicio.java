package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface ClienteServicio {

    Cliente iniciarSesion(String email, String contrasena) throws Exception;

    Cliente registrar(Cliente cliente) throws Exception;

    Cliente activarCuenta(String email, String fecha) throws Exception;

    Cliente actualizar(Cliente cliente) throws Exception;

    void eliminar(String cedulaCliente) throws Exception;

    Cliente obtener(String cedulaCliente) throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarCompras(String cedulaCliente) throws Exception;

    Compra redimirCupon(Integer idCuponCliente, Compra compra) throws Exception;

    CuponCliente obtenerCuponCliente(Integer idCuponCliente) throws Exception;

    List<Pelicula> buscarPeliculas(String busqueda, Integer idCiudad) throws Exception;

    Compra registrarCompra(Compra compra) throws Exception;

    void enviarCorreoCambioContrasena(String emailCliente) throws Exception;

    void cambiarContrasena(String email, String fecha, String nuevaContrasena) throws Exception;

    CuponCliente agregarCupon(String nombreCupon, Cliente cliente) throws Exception;

    void agregarCuponCorreo(String nombreCupon, String emailCliente) throws Exception;

    List<Ciudad> obtenerCiudades();

    Ciudad obtenerCiudad(Integer idCiudad) throws Exception;

    List<Entrada> obtenerEntradasCompradas(Funcion funcion) throws Exception;

    List<Pelicula> filtrarPeliculasEstadoCiudad(Integer idCiudad, EstadoPelicula estado) throws Exception;

    List<Funcion> obtenerFuncionesPelicula(Integer idPelicula) throws Exception;

    List<CuponCliente> obtenerCuponesCliente(Cliente cliente) throws Exception;

    List<CuponCliente> obtenerCuponesClienteEstado(Cliente cliente, EstadoCupon disponible) throws Exception;

    CuponCliente actualizarCuponCliente(CuponCliente cuponCliente) throws Exception;

    Compra obtenerCompra(Integer idCompra) throws Exception;

    boolean cuponVencido(Cupon cupon);
}
