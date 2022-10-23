package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
import co.edu.uniquindio.unicine.repo.ConfiteriaRepo;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio {
    // Repositorios sobre los cuales se haran las consultas
    private final AdministradorTeatroRepo administradorTeatroRepo;
    private final PeliculaRepo peliculaRepo;
    private final ConfiteriaRepo confiteriaRepo;
    private final CuponRepo cuponRepo;

    String EMAIL_ADMINISTRADOR = "administradorunicine@gmail.com";
    String CONTRASENA_ADMINISTRADOR = "samuelyfelipe";

    public AdministradorServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, PeliculaRepo peliculaRepo,
                                     ConfiteriaRepo confiteriaRepo, CuponRepo cuponRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.cuponRepo = cuponRepo;
    }

    @Override
    public boolean iniciarSesion(String email, String contrasena) {

        if(email.equals(EMAIL_ADMINISTRADOR) && contrasena.equals(CONTRASENA_ADMINISTRADOR)){
            return true;
        }
        return false;
    }

    // Gestionar administradores de teatros ----------------------------------------------------------------------
    @Override
    public AdministradorTeatro crearAdministrador(AdministradorTeatro administradorTeatro) throws Exception {
        String administradorTeatroId = administradorTeatro.getCedula();

        if(administradorTeatroRepo.findById(administradorTeatroId).isPresent())
            throw new Exception("El administrador de teatro con el id" + administradorTeatroId + " ya existe");

        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public AdministradorTeatro actualizarAdministrador(AdministradorTeatro administradorTeatro) throws Exception {
        Optional<AdministradorTeatro> administradorTeatroGuardado = administradorTeatroRepo.findById(administradorTeatro.getCedula());

        if(administradorTeatroGuardado.isEmpty()) throw new Exception("El administrador no existe");

        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public void eliminarAdministrador(String cedulaAdministrador) throws Exception {
        Optional<AdministradorTeatro> administradorTeatroGuardado = administradorTeatroRepo.findById(cedulaAdministrador);

        if(administradorTeatroGuardado.isEmpty()) throw new Exception("El administrador de teatro no existe");

        administradorTeatroRepo.delete(administradorTeatroGuardado.get());
    }

    @Override
    public AdministradorTeatro obtenerAdministrador(String cedulaAdministrador) throws Exception {
        Optional<AdministradorTeatro> administradorTeatroGuardado = administradorTeatroRepo.findById(cedulaAdministrador);

        if(administradorTeatroGuardado.isEmpty()) throw new Exception("El administrador de teatro no existe");

        return administradorTeatroGuardado.get();
    }

    @Override
    public List<AdministradorTeatro> listarAdministradores() {
        List<AdministradorTeatro> administradoresTeatros = administradorTeatroRepo.findAll();
        return administradoresTeatros;
    }

    // Gestionar peliculas ---------------------------------------------------------------------------------------
    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {
        String peliculaId = pelicula.getNombre();

        if(peliculaRepo.findById(peliculaId).isPresent())
            throw new Exception("La pelicula con el id" + peliculaId + " ya existe");

        return peliculaRepo.save(pelicula);
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(pelicula.getNombre());

        if(peliculaGuardada.isEmpty()) throw new Exception("La pelicula no existe");

        return peliculaRepo.save(pelicula);
    }

    @Override
    public void eliminarPelicula(String nombrePelicula) throws Exception {
        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(nombrePelicula);

        if(peliculaGuardada.isEmpty()) throw new Exception("La pelicula no existe");

        peliculaRepo.delete(peliculaGuardada.get());
    }

    @Override
    public Pelicula obtenerPelicula(String nombrePelicula) throws Exception {
        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(nombrePelicula);
        return peliculaGuardada.orElse(null);
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        List<Pelicula> peliculas = peliculaRepo.findAll();
        return peliculas;
    }

    // Gestionar confiteria --------------------------------------------------------------------------------------
    @Override
    public Confiteria crearComestible(Confiteria comestible) throws Exception {
        if(comestible == null) throw new Exception("No hay datos del comestible");

        if(confiteriaRepo.findByNombre(comestible.getNombre()) != null)
            throw new Exception("El comestible ya existe");

        return confiteriaRepo.save(comestible);
    }

    @Override
    public Confiteria actualizarComestible(Confiteria comestible) throws Exception {
        Optional<Confiteria> comestibleGuardado = confiteriaRepo.findById(comestible.getId());

        if(comestibleGuardado.isEmpty()) throw new Exception("El comestible no existe");

        return confiteriaRepo.save(comestible);
    }

    @Override
    public void eliminarComestible(Integer idComestible) throws Exception {
        Optional<Confiteria> comestibleGuardado = confiteriaRepo.findById(idComestible);

        if(comestibleGuardado.isEmpty()) throw new Exception("El comestible no existe");

        confiteriaRepo.delete(comestibleGuardado.get());
    }

    @Override
    public Confiteria obtenerComestible(Integer idComestible) throws Exception {
        Optional<Confiteria> comestibleGuardado = confiteriaRepo.findById(idComestible);
        return comestibleGuardado.orElse(null);
    }

    @Override
    public List<Confiteria> listarConfiteria() {
        List<Confiteria> confiteria = confiteriaRepo.findAll();
        return confiteria;
    }

    // Gestionar cupones -----------------------------------------------------------------------------------------
    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {

        if(cuponRepo.findByNombre(cupon.getNombre()) != null)
            throw new Exception("El cupon ya existe");

        return cuponRepo.save(cupon);
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {
        Optional<Cupon> cuponGuardado = cuponRepo.findById(cupon.getId());

        if(cuponGuardado.isEmpty()) throw new Exception("El cupon no existe");

        return cuponRepo.save(cupon);
    }

    @Override
    public void eliminarCupon(Integer idCupon) throws Exception {
        Optional<Cupon> cuponGuardado = cuponRepo.findById(idCupon);
        if(cuponGuardado.isEmpty()) throw new Exception("El cupon no existe");

        cuponRepo.delete(cuponGuardado.get());
    }

    @Override
    public Cupon obtenerCupon(Integer idCupon) throws Exception {
        Optional<Cupon> cuponGuardado = cuponRepo.findById(idCupon);
        return cuponGuardado.orElse(null);
    }

    @Override
    public List<Cupon> listarCupones() {
        List<Cupon> cupones = cuponRepo.findAll();
        return cupones;
    }
}