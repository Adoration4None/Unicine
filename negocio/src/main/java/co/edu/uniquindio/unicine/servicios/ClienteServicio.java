package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ClienteServicio {

    Cliente iniciarSesion(String email, String contrasena) throws Exception;

    Cliente registrar(Cliente cliente) throws Exception;

    Cliente activarCuenta(Cliente cliente) throws Exception;

    Cliente actualizar(Cliente cliente) throws Exception;

    void eliminar(String cedulaCliente) throws Exception;

    Cliente obtener(String cedulaCliente) throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarCompras(String cedulaCliente) throws Exception;

    Compra redimirCupon(Integer idCuponCliente, Compra compra) throws Exception;

    List<Funcion> buscarFunciones(String busqueda, Integer idCiudad) throws Exception;

    Compra iniciarCompra(Cliente cliente, Funcion funcion) throws Exception;

    Compra asignarAsientosCompra(Compra compra, List<Entrada> entradas) throws Exception;

    Compra comprarConfiteria(Compra compra, List<CompraConfiteria> confiteria) throws Exception;

    Compra elegirMetodoPago(Compra compra, MetodoPago metodoPago) throws Exception;

    Compra finalizarCompra(Compra compra) throws Exception;

    boolean cambiarContrasena(String emailCliente) throws Exception;

    CuponCliente agregarCupon(String nombreCupon, Cliente cliente) throws Exception;

    List<Funcion> filtrarFuncionesCiudad(Integer idCiudad) throws Exception;

    List<Ciudad> obtenerCiudades();

    Ciudad obtenerCiudad(Integer idCiudad) throws Exception;

    List<Entrada> obtenerEntradasCompradas(Funcion funcion) throws Exception;

}
