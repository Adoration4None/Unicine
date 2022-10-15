package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
import co.edu.uniquindio.unicine.repo.SalaRepo;
import co.edu.uniquindio.unicine.repo.TeatroRepo;

import java.util.List;

public class AdminTeatroServicioImpl implements AdminTeatroServicio {
    // Repositorios sobre los cuales se haran las consultas
    private final TeatroRepo teatroRepo;
    private final SalaRepo salaRepo;
    private final FuncionRepo funcionRepo;

    public AdminTeatroServicioImpl(TeatroRepo teatroRepo, SalaRepo salaRepo, FuncionRepo funcionRepo) {
        this.teatroRepo = teatroRepo;
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
    }

    @Override
    public AdministradorTeatro iniciarSesion(String email, String contrasena) throws Exception {
        return null;
    }

    // Gestionar teatros --------------------------------------------------------------------------------------
    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {
        return null;
    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {
        return null;
    }

    @Override
    public void eliminarTeatro(Integer idTeatro) throws Exception {

    }

    @Override
    public Teatro obtenerTeatro(Integer idTeatro) throws Exception {
        return null;
    }

    @Override
    public List<Teatro> listarTeatros() {
        return null;
    }


    // Gestionar salas ----------------------------------------------------------------------------------------
    @Override
    public Sala crearSala(Sala sala) throws Exception {
        return null;
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        return null;
    }

    @Override
    public void eliminarSala(Integer idSala) throws Exception {

    }

    @Override
    public Sala obtenerSala(Integer idSala) throws Exception {
        return null;
    }

    @Override
    public List<Sala> listarSalas() {
        return null;
    }


    // Gestionar funciones ------------------------------------------------------------------------------------
    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {
        return null;
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        return null;
    }

    @Override
    public void eliminarFuncion(Integer idFuncion) throws Exception {

    }

    @Override
    public Funcion obtenerFuncion(Integer idFuncion) throws Exception {
        return null;
    }

    @Override
    public List<Funcion> listarFunciones() {
        return null;
    }
}
