package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ClienteServicio {

    Cliente iniciarSesion(String email, String contrasena) throws Exception;

    Cliente registrar(Cliente cliente) throws Exception;

    Cliente activarCuenta(Cliente cliente) throws Exception;

    Cliente actualizar(Cliente cliente) throws Exception;

    void eliminar(String cedulaCliente) throws Exception;

    Cliente obtener(String cedulaCliente) throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarCompras(String cedulaCliente) throws Exception;

    Compra redimirCupon(Integer idCupon, Compra compra) throws Exception;

    List<Pelicula> buscarPeliculas(String busqueda) throws Exception;

    Compra iniciarCompra(Cliente cliente, Funcion funcion) throws Exception;

    Compra asignarAsientosCompra(Compra compra, List<Entrada> entradas) throws Exception;

    Compra comprarConfiteria(Compra compra, List<CompraConfiteria> confiteria) throws Exception;

    Compra elegirMetodoPago(Compra compra, MetodoPago metodoPago) throws Exception;

    Compra finalizarCompra(Compra compra, LocalDateTime fechaCompra) throws Exception;

    boolean cambiarContrasena(String emailCliente) throws Exception;

    Cupon agregarCupon(String nombreCupon, Cliente cliente) throws Exception;

    public List<Pelicula> filtrarPeliculasCiudad(Integer idCiudad) throws Exception;
}
