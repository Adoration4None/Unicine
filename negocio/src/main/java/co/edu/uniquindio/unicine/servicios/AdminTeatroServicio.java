package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.Teatro;

import java.util.List;

public interface AdminTeatroServicio {

    AdministradorTeatro iniciarSesion(String email, String contrasena) throws Exception;

    // Gestionar teatros ----------------------------------------------------------------------------------------
    Teatro crearTeatro(Teatro teatro) throws Exception;

    Teatro actualizarTeatro(Teatro teatro) throws Exception;

    void eliminarTeatro(Integer idTeatro) throws Exception;

    Teatro obtenerTeatro(Integer idTeatro) throws Exception;

    List<Teatro> listarTeatros();


    // Gestionar salas ------------------------------------------------------------------------------------------
    Sala crearSala(Sala sala) throws Exception;

    Sala actualizarSala(Sala sala) throws Exception;

    void eliminarSala(Integer idSala) throws Exception;

    Sala obtenerSala(Integer idSala) throws Exception;

    List<Sala> listarSalas();


    // Gestionar funciones --------------------------------------------------------------------------------------
    Funcion crearFuncion(Funcion funcion) throws Exception;

    Funcion actualizarFuncion(Funcion funcion) throws Exception;

    void eliminarFuncion(Integer idFuncion) throws Exception;

    Funcion obtenerFuncion(Integer idFuncion) throws Exception;

    List<Funcion> listarFunciones();

}
