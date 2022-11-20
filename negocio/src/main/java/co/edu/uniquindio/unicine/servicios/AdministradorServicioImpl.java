package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
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
    private final GeneroRepo generoRepo;
    private final CiudadRepo ciudadRepo;

    String EMAIL_ADMINISTRADOR = "administradorunicine@gmail.com";
    String CONTRASENA_ADMINISTRADOR = "samuelyfelipe";

    public AdministradorServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, PeliculaRepo peliculaRepo,
                                     ConfiteriaRepo confiteriaRepo, CuponRepo cuponRepo, GeneroRepo generoRepo, CiudadRepo ciudadRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.cuponRepo = cuponRepo;
        this.generoRepo = generoRepo;
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public Persona iniciarSesion(String email, String contrasena) throws Exception {
        if( (email == null || email.equals("")) || (contrasena == null || contrasena.equals("")) )
            throw new Exception("Datos incompletos");

        if( !email.equals(EMAIL_ADMINISTRADOR) && !contrasena.equals(CONTRASENA_ADMINISTRADOR) )
            return null;

        return new Persona( "1111", "Ricardo Salinas", EMAIL_ADMINISTRADOR, CONTRASENA_ADMINISTRADOR);
    }

    // Gestionar administradores de teatros ----------------------------------------------------------------------
    @Override
    public AdministradorTeatro crearAdministrador(AdministradorTeatro administradorTeatro) throws Exception {
        String administradorTeatroId = administradorTeatro.getCedula();

        if(administradorTeatroRepo.findById(administradorTeatroId).isPresent())
            throw new Exception("El administrador de teatro con cedula " + administradorTeatroId + " ya existe");

        // Setteo del estado inactivo para mayor seguridad
        administradorTeatro.setEstado(EstadoPersona.INACTIVO);

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
        return administradorTeatroRepo.findAll();
    }

    // Gestionar peliculas ---------------------------------------------------------------------------------------
    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {
        if(pelicula == null) throw new Exception("Pelicula vacia");

        return peliculaRepo.save(pelicula);
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        if(pelicula == null) throw new Exception("Pelicula vacia");

        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(pelicula.getId());

        if(peliculaGuardada.isEmpty()) throw new Exception("La pelicula no existe");

        return peliculaRepo.save(pelicula);
    }

    @Override
    public void eliminarPelicula(Integer idPelicula) throws Exception {
        if(idPelicula == null || idPelicula.equals(0)) throw new Exception("ID de pelicula vacio");

        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(idPelicula);

        if(peliculaGuardada.isEmpty()) throw new Exception("La pelicula no existe");

        peliculaRepo.delete(peliculaGuardada.get());
    }

    @Override
    public Pelicula obtenerPelicula(Integer idPelicula) throws Exception {
        if(idPelicula == null || idPelicula.equals(0) ) throw new Exception("ID de pelicula vacio");

        Pelicula peliculaGuardada = peliculaRepo.findById(idPelicula).orElse(null);

        if(peliculaGuardada == null) throw new Exception("La pelicula no existe en la base de datos");

        return peliculaGuardada;
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return peliculaRepo.findAll();
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
        if(idComestible == null || idComestible.equals(0)) throw new Exception("ID del comestible vacio");

        Confiteria comestibleGuardado = confiteriaRepo.findById(idComestible).orElse(null);

        if(comestibleGuardado == null) throw new Exception("El comestible no existe en la base de datos");

        return comestibleGuardado;
    }

    @Override
    public List<Confiteria> listarConfiteria() {
        return confiteriaRepo.findAll();
    }


    // Gestionar cupones -----------------------------------------------------------------------------------------
    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {
        if(cupon == null) throw new Exception("No hay cupon para crear");
        if(cuponRepo.findByNombre(cupon.getNombre()) != null) throw new Exception("El cupon ya existe");

        return cuponRepo.save(cupon);
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {
        if(cupon == null) throw new Exception("No hay cupon para actualizar");

        Optional<Cupon> cuponGuardado = cuponRepo.findById(cupon.getId());

        if(cuponGuardado.isEmpty()) throw new Exception("El cupon no existe");

        return cuponRepo.save(cupon);
    }

    @Override
    public void eliminarCupon(Integer idCupon) throws Exception {
        if(idCupon == null || idCupon.equals(0)) throw new Exception("ID de cupon vacio");

        Optional<Cupon> cuponGuardado = cuponRepo.findById(idCupon);
        if(cuponGuardado.isEmpty()) throw new Exception("El cupon no existe");

        cuponRepo.delete(cuponGuardado.get());
    }

    @Override
    public Cupon obtenerCupon(Integer idCupon) throws Exception {
        if(idCupon == null || idCupon.equals(0)) throw new Exception("ID de cupon vacio");

        Cupon cuponGuardado = cuponRepo.findById(idCupon).orElse(null);

        if(cuponGuardado == null) throw new Exception("El cupon no existe en la base de datos");

        return cuponGuardado;
    }

    @Override
    public List<Cupon> listarCupones() {
        return cuponRepo.findAll();
    }


    // Opciones de genero de pelicula --------------------------------------------------------------------------
    @Override
    public Genero crearGenero(Genero genero) throws Exception {
        if(genero == null) throw new Exception("No hay genero para crear");
        if( generoRepo.findByNombre(genero.getNombre()) != null ) throw new Exception("El genero ya existe");

        return generoRepo.save(genero);
    }

    @Override
    public Genero actualizarGenero(Genero genero) throws Exception {
        Optional<Genero> generoGuardado = generoRepo.findById(genero.getId());

        if(generoGuardado.isEmpty()) throw new Exception("El genero no existe");

        return generoRepo.save(genero);
    }

    @Override
    public void eliminarGenero(Integer idGenero) throws Exception {
        Optional<Genero> generoGuardado = generoRepo.findById(idGenero);

        if(generoGuardado.isEmpty()) throw new Exception("El genero no existe");

        generoRepo.delete(generoGuardado.get());
    }

    @Override
    public List<Genero> obtenerGeneros() {
        return generoRepo.findAll();
    }


    // Opciones de ciudad ---------------------------------------------------------------------------------------
    @Override
    public Ciudad crearCiudad(Ciudad ciudad) throws Exception {
        if(ciudad == null) throw new Exception("No hay ciudad para crear");
        if( ciudadRepo.findByNombreAndDepartamento(ciudad.getNombre(), ciudad.getDepartamento()) != null )
            throw new Exception("La ciudad ya existe");

        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer idCiudad) throws Exception {
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("ID de ciudad vacio");

        Ciudad ciudadGuardada = ciudadRepo.findById(idCiudad).orElse(null);

        if(ciudadGuardada == null) throw new Exception("La ciudad no existe en la base de datos");

        return ciudadGuardada;
    }

    @Override
    public void eliminarCiudad(Integer idCiudad) throws Exception {
        Optional<Ciudad> ciudadGuardada = ciudadRepo.findById(idCiudad);

        if(ciudadGuardada.isEmpty()) throw new Exception("La ciudad no existe");

        ciudadRepo.delete(ciudadGuardada.get());
    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws Exception {
        Optional<Ciudad> ciudadGuardada = ciudadRepo.findById(ciudad.getId());

        if(ciudadGuardada.isEmpty()) throw new Exception("La ciudad no existe");

        return ciudadRepo.save(ciudad);
    }


    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }
}