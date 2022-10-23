package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
import co.edu.uniquindio.unicine.repo.SalaRepo;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminTeatroServicioImpl implements AdminTeatroServicio {
    // Repositorios sobre los cuales se haran las consultas
    private final TeatroRepo teatroRepo;
    private final SalaRepo salaRepo;
    private final FuncionRepo funcionRepo;

    private final AdministradorTeatroRepo adminTeatroRepo;

    public AdminTeatroServicioImpl(TeatroRepo teatroRepo, SalaRepo salaRepo, FuncionRepo funcionRepo, AdministradorTeatroRepo adminTeatroRepo) {
        this.teatroRepo = teatroRepo;
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
        this.adminTeatroRepo = adminTeatroRepo;
    }

    @Override
    public AdministradorTeatro iniciarSesion(String email, String contrasena) throws Exception {
        AdministradorTeatro administradorTeatroEncontrado = adminTeatroRepo.findByEmailAndContrasena(email, contrasena);

        if(administradorTeatroEncontrado == null) throw new Exception("Datos de autenticacion incorrectos");

        return administradorTeatroEncontrado;
    }

    // Gestionar teatros --------------------------------------------------------------------------------------
    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {

        if(teatro == null) throw new Exception("No hay datos del teatro a crear");

        if(teatroRepo.findByNombreAndDireccionAndCiudad(teatro.getNombre(), teatro.getDireccion(), teatro.getCiudad()) != null)
            throw new Exception("El teatro ya existe");

        return teatroRepo.save(teatro);
    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {
        Optional<Teatro> teatroGuardado = teatroRepo.findById(teatro.getId());

        if(teatroGuardado.isEmpty()) throw new Exception("El teatro no existe");

        return teatroRepo.save(teatro);
    }

    @Override
    public void eliminarTeatro(Integer idTeatro) throws Exception {
        Optional<Teatro> teatroGuardado = teatroRepo.findById(idTeatro);

        if(teatroGuardado.isEmpty()) throw new Exception("El teatro no existe");

        teatroRepo.delete(teatroGuardado.get());
    }

    @Override
    public Teatro obtenerTeatro(Integer idTeatro) throws Exception {
        Optional<Teatro> teatroGuardado = teatroRepo.findById(idTeatro);
        return teatroGuardado.orElse(null);
    }

    @Override
    public List<Teatro> listarTeatros() {
        List<Teatro> teatros = teatroRepo.findAll();
        return teatros;
    }


    // Gestionar salas ----------------------------------------------------------------------------------------
    @Override
    public Sala crearSala(Sala sala) throws Exception {
        Integer salaId = sala.getId();

        if(salaRepo.findById(salaId).isPresent()) throw new Exception("La sala con el id" + salaId + " ya existe");

        return salaRepo.save(sala);
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        Optional<Sala> salaGuardada = salaRepo.findById(sala.getId());

        if(salaGuardada.isEmpty()) throw new Exception("La sala no existe");

        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Integer idSala) throws Exception {
        Optional<Sala> salaGuardada = salaRepo.findById(idSala);

        if(salaGuardada.isEmpty()) throw new Exception("La sala no existe");

        salaRepo.delete(salaGuardada.get());
    }

    @Override
    public Sala obtenerSala(Integer idSala) throws Exception {
        Optional<Sala> salaGuardada = salaRepo.findById(idSala);
        return salaGuardada.orElse(null);
    }

    @Override
    public List<Sala> listarSalas() {
        List<Sala> salas = salaRepo.findAll();
        return salas;
    }

    // Gestionar funciones ------------------------------------------------------------------------------------
    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {
        Integer funcionId = funcion.getId();

        if( funcionRepo.findByPeliculaAndSalaAndHorario(funcion.getPelicula(), funcion.getSala(), funcion.getHorario()) != null)
            throw new Exception("La funcion ya existe");

        return funcionRepo.save(funcion);
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        Optional<Funcion> funcionGuardada = funcionRepo.findById(funcion.getId());

        if(funcionGuardada.isEmpty()) throw new Exception("La funciÃ³n no existe");

        return funcionRepo.save(funcion);
    }

    @Override
    public void eliminarFuncion(Integer idFuncion) throws Exception {
        Optional<Funcion> funcionGuardada = funcionRepo.findById(idFuncion);

        if(funcionGuardada.isEmpty()) throw new Exception("La funcion no existe");

        funcionRepo.delete(funcionGuardada.get());
    }

    @Override
    public Funcion obtenerFuncion(Integer idFuncion) throws Exception {
        Optional<Funcion> funcionGuardada = funcionRepo.findById(idFuncion);
        return funcionGuardada.orElse(null);
    }

    @Override
    public List<Funcion> listarFunciones() {
        List<Funcion> funciones = funcionRepo.findAll();
        return funciones;
    }
}