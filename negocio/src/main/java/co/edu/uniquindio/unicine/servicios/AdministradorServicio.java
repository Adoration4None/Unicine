package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdministradorServicio {

    Persona iniciarSesion(String email, String contrasena) throws Exception;

    // Gestionar administradores de teatros --------------------------------------------------------------------
    AdministradorTeatro crearAdministrador(AdministradorTeatro administradorTeatro) throws Exception;

    AdministradorTeatro actualizarAdministrador(AdministradorTeatro administradorTeatro) throws Exception;

    void eliminarAdministrador(String cedulaAdministrador) throws Exception;

    AdministradorTeatro obtenerAdministrador(String cedulaAdministrador) throws Exception;

    List<AdministradorTeatro> listarAdministradores();


    // Gestionar peliculas --------------------------------------------------------------------------------------
    Pelicula crearPelicula(Pelicula pelicula) throws Exception;

    Pelicula actualizarPelicula(Pelicula pelicula) throws Exception;

    void eliminarPelicula(String nombrePelicula) throws Exception;

    Pelicula obtenerPelicula(String nombrePelicula) throws Exception;

    List<Pelicula> listarPeliculas();


    // Gestionar confiteria -------------------------------------------------------------------------------------
    Confiteria crearComestible(Confiteria comestible) throws Exception;

    Confiteria actualizarComestible(Confiteria comestible) throws Exception;

    void eliminarComestible(Integer idComestible) throws Exception;

    Confiteria obtenerComestible(Integer idComestible) throws Exception;

    List<Confiteria> listarConfiteria();


    // Gestionar cupones ----------------------------------------------------------------------------------------
    Cupon crearCupon(Cupon cupon) throws Exception;

    Cupon actualizarCupon(Cupon cupon) throws Exception;

    void eliminarCupon(Integer idCupon) throws Exception;

    Cupon obtenerCupon(Integer idCupon) throws Exception;

    List<Cupon> listarCupones();


    // Opciones de genero de pelicula -------------------------------------------------------------------------
    Genero crearGenero(Genero genero) throws Exception;

    List<Genero> obtenerGeneros();
}
