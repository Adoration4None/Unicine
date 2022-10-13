package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
import co.edu.uniquindio.unicine.repo.ConfiteriaRepo;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;

import java.util.List;

public class AdministradorServicioImpl implements AdministradorServicio {
    // Repositorios sobre los cuales se haran las consultas
    private final AdministradorTeatroRepo administradorTeatroRepo;
    private final PeliculaRepo peliculaRepo;
    private final ConfiteriaRepo confiteriaRepo;
    private final CuponRepo cuponRepo;

    public AdministradorServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, PeliculaRepo peliculaRepo,
                                     ConfiteriaRepo confiteriaRepo, CuponRepo cuponRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.cuponRepo = cuponRepo;
    }

    @Override
    public boolean iniciarSesion(String email, String contrasena) throws Exception {
        return false;
    }

    // Gestionar administradores de teatros ----------------------------------------------------------------------
    @Override
    public AdministradorTeatro crearAdministrador(AdministradorTeatro administradorTeatro) throws Exception {
        return null;
    }

    @Override
    public AdministradorTeatro actualizarAdministrador(AdministradorTeatro administradorTeatro) throws Exception {
        return null;
    }

    @Override
    public void eliminarAdministrador(String cedulaAdministrador) throws Exception {

    }

    @Override
    public AdministradorTeatro obtenerAdministrador(String cedulaAdministrador) throws Exception {
        return null;
    }

    @Override
    public List<AdministradorTeatro> listarAdministradores() {
        return null;
    }


    // Gestionar peliculas ---------------------------------------------------------------------------------------
    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {
        return null;
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        return null;
    }

    @Override
    public void eliminarPelicula(String nombrePelicula) throws Exception {

    }

    @Override
    public Pelicula obtenerPelicula(String nombrePelicula) throws Exception {
        return null;
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return null;
    }


    // Gestionar confiteria --------------------------------------------------------------------------------------
    @Override
    public Confiteria crearComestible(Confiteria comestible) throws Exception {
        return null;
    }

    @Override
    public Confiteria actualizarComestible(Confiteria comestible) throws Exception {
        return null;
    }

    @Override
    public void eliminarComestible(Integer idComestible) throws Exception {

    }

    @Override
    public Confiteria obtenerComestible(Integer idComestible) throws Exception {
        return null;
    }

    @Override
    public List<Confiteria> listarConfiteria() {
        return null;
    }


    // Gestionar cupones -----------------------------------------------------------------------------------------
    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {
        return null;
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {
        return null;
    }

    @Override
    public void eliminarCupon(Integer idCupon) throws Exception {

    }

    @Override
    public Cupon obtenerCupon(Integer idCupon) throws Exception {
        return null;
    }

    @Override
    public List<Cupon> listarCupones() {
        return null;
    }
}
