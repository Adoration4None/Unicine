package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminTeatroServicioImpl implements AdminTeatroServicio {
    // Repositorios sobre los cuales se haran las consultas
    private final TeatroRepo teatroRepo;
    private final SalaRepo salaRepo;
    private final FuncionRepo funcionRepo;

    private final AdministradorTeatroRepo adminTeatroRepo;
    private final CiudadRepo ciudadRepo;
    private final HorarioRepo horarioRepo;
    private final PeliculaRepo peliculaRepo;

    public AdminTeatroServicioImpl(TeatroRepo teatroRepo, SalaRepo salaRepo, FuncionRepo funcionRepo,
                                   AdministradorTeatroRepo adminTeatroRepo, CiudadRepo ciudadRepo, HorarioRepo horarioRepo,
                                   PeliculaRepo peliculaRepo) {
        this.teatroRepo = teatroRepo;
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
        this.adminTeatroRepo = adminTeatroRepo;
        this.ciudadRepo = ciudadRepo;
        this.horarioRepo = horarioRepo;
        this.peliculaRepo = peliculaRepo;
    }

    @Override
    public AdministradorTeatro iniciarSesion(String email, String contrasena) throws Exception {
        if( (email == null || email.equals("")) || (contrasena == null || contrasena.equals("")) )
            throw new Exception("Datos incompletos");

        AdministradorTeatro administradorTeatroEncontrado = adminTeatroRepo.findByEmail(email);

        if(administradorTeatroEncontrado == null) return null;

        if( administradorTeatroEncontrado.getEstado() == EstadoPersona.INACTIVO )
            throw new Exception("Administrador inactivo");

        // Comprobar contraseña encriptada
        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();

        if( !spe.checkPassword(contrasena, administradorTeatroEncontrado.getContrasena() ) )
            throw new Exception("La contraseña es incorrecta");

        return administradorTeatroEncontrado;
    }

    // Gestionar teatros --------------------------------------------------------------------------------------
    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {
        if(teatro == null) throw new Exception("No hay datos del teatro a crear");

        if(teatroRepo.findByNombreAndDireccionAndCiudad(teatro.getNombre(), teatro.getDireccion(), teatro.getCiudad()) != null)
            throw new Exception("El teatro ya existe");

        ciudadRepo.save( teatro.getCiudad() );

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
        if(idTeatro == null || idTeatro.equals(0)) throw new Exception("ID de teatro vacio");

        Teatro teatroGuardado = teatroRepo.findById(idTeatro).orElse(null);

        if(teatroGuardado == null) throw new Exception("El teatro no existe en la base de datos");

        return teatroGuardado;
    }

    @Override
    public List<Teatro> listarTeatros() {
        return teatroRepo.findAll();
    }


    // Gestionar salas ----------------------------------------------------------------------------------------
    @Override
    public Sala crearSala(Sala sala) throws Exception {
        if(sala == null) throw new Exception("No hay sala para crear");

        if( cantidadSillasInvalida(sala) )
            throw new Exception("Las filas y las columnas de la sala no coinciden con su cantidad de sillas");

        return salaRepo.save(sala);
    }

    private boolean cantidadSillasInvalida(Sala sala) {
        return sala.getCantidadSillas() != sala.getFilas() * sala.getColumnas();
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        if(sala == null) throw new Exception("No hay sala para actualizar");
        Optional<Sala> salaGuardada = salaRepo.findById(sala.getId());

        if(cantidadSillasInvalida(sala)){
            throw new Exception("Las filas y las columnas de la sala no coinciden con su cantidad de sillas");
        }
        else if(salaGuardada.isEmpty()){
            throw new Exception("La sala no existe");
        }

        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Integer idSala) throws Exception {
        if(idSala == null || idSala.equals(0)) throw new Exception("ID de sala vacio");

        Optional<Sala> salaGuardada = salaRepo.findById(idSala);

        if(salaGuardada.isEmpty()) throw new Exception("La sala no existe");

        salaRepo.delete(salaGuardada.get());
    }

    @Override
    public Sala obtenerSala(Integer idSala) throws Exception {
        if(idSala == null || idSala.equals(0)) throw new Exception("ID de sala vacio");

        Sala salaGuardada = salaRepo.findById(idSala).orElse(null);

        if(salaGuardada == null) throw new Exception("La sala no existe en la base de datos");

        return salaGuardada;
    }

    @Override
    public List<Sala> listarSalas() {
        return salaRepo.findAll();
    }

    // Gestionar funciones ------------------------------------------------------------------------------------
    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {

        if( funcionRepo.findByPeliculaAndSalaAndHorario(funcion.getPelicula(), funcion.getSala(), funcion.getHorario()) != null)
            throw new Exception("La funcion ya existe");

        if( tiposNoCoinciden(funcion.getTipo(), funcion.getSala().getTipo()) )
            throw new Exception("El tipo de la funcion no corresponde con el tipo de la sala");

        if( salaOcupadaHorario(funcion.getSala(), funcion.getHorario()) )
            throw new Exception("La sala seleccionada ya se encuentra ocupada en el horario seleccionado");

        return funcionRepo.save(funcion);
    }

    private boolean salaOcupadaHorario(Sala sala, Horario horario) {
        return funcionRepo.obtenerFuncionesSalaHorario( sala.getId(), horario.getId() ).size() > 0;
    }

    private boolean tiposNoCoinciden(TipoFuncion tipoFuncion, TipoSala tipoSala) {
        Map<TipoFuncion, TipoSala> tiposCorrespondientes = new HashMap<>();
        tiposCorrespondientes.put(TipoFuncion.FUNCION_2D, TipoSala.SALA_2D);
        tiposCorrespondientes.put(TipoFuncion.FUNCION_3D, TipoSala.SALA_3D);
        tiposCorrespondientes.put(TipoFuncion.FUNCION_XD, TipoSala.SALA_XD);

        return tipoSala != tiposCorrespondientes.get(tipoFuncion);
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        Optional<Funcion> funcionGuardada = funcionRepo.findById(funcion.getId());

        if(funcionGuardada.isEmpty()) throw new Exception("La funcion no existe");

        if( tiposNoCoinciden(funcion.getTipo(), funcion.getSala().getTipo()) )
            throw new Exception("El tipo de la funcion no corresponde con el tipo de la sala");

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
        if(idFuncion == null || idFuncion.equals(0)) throw new Exception("ID de funcion vacio");

        Funcion funcionGuardada = funcionRepo.findById(idFuncion).orElse(null);

        if(funcionGuardada == null) throw new Exception("La funcion no existe en la base de datos");

        return funcionGuardada;
    }

    @Override
    public List<Funcion> listarFunciones() {
        return funcionRepo.findAll();
    }


    // Opciones de horario -------------------------------------------------------------------------------------
    @Override
    public Horario crearHorario(Horario horario) throws Exception {
        if(horario == null) throw new Exception("No hay horario para crear");
        if(horarioRepo.findByFechaAndHora(horario.getFecha(), horario.getHora()) != null)
            throw new Exception("El horario ya existe");

        return horarioRepo.save(horario);
    }

    @Override
    public List<Horario> listarHorarios() { return horarioRepo.findAll(); }

    @Override
    public void eliminarHorario(Integer idHorario) throws Exception {
        Optional<Horario> horarioGuardado = horarioRepo.findById(idHorario);

        if(horarioGuardado.isEmpty()) throw new Exception("El horario no existe");

        horarioRepo.delete(horarioGuardado.get());
    }

    @Override
    public Horario actualizarHorario(Horario horario) throws Exception {
        Optional<Horario> horarioGuardado = horarioRepo.findById(horario.getId());

        if(horarioGuardado.isEmpty()) throw new Exception("El horario no existe");

        return horarioRepo.save(horario);
    }

    @Override
    public Horario obtenerHorario(Integer idHorario) throws Exception {
        if(idHorario == null || idHorario.equals(0)) throw new Exception("ID de horario vacio");

        Horario horarioGuardado = horarioRepo.findById(idHorario).orElse(null);

        if(horarioGuardado == null) throw new Exception("El horario no existe en la base de datos");

        return horarioGuardado;
    }


    // Otras consultas -----------------------------------------------------------------------------------------
    @Override
    public List<Teatro> obtenerTeatrosCiudadAdmin(Integer idCiudad) throws Exception {
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("ID de la ciudad vacio");

        return teatroRepo.obtenerTeatrosCiudadAdmin(idCiudad);
    }

    @Override
    public List<Teatro> obtenerTeatrosCiudad(Integer idCiudad) throws Exception {
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("ID de la ciudad vacio");

        return teatroRepo.obtenerTeatrosCiudad(idCiudad);
    }

    @Override
    public List<Teatro> obtenerTeatrosCiudadPelicula(Integer idCiudad, Integer idPelicula) throws Exception {
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("ID de la ciudad vacio");
        if(idPelicula == null || idPelicula.equals(0)) throw new Exception("ID de la pelicula vacio");

        return teatroRepo.obtenerTeatrosCiudad(idCiudad);
    }

    @Override
    public List<Sala> obtenerSalasCiudad(Integer idCiudad) throws Exception {
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("ID de la ciudad vacio");

        return salaRepo.obtenerSalasCiudad(idCiudad);
    }

    @Override
    public List<Funcion> obtenerFuncionesCiudad(Integer idCiudad) throws Exception {
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("ID de la ciudad vacio");

        return funcionRepo.obtenerFuncionesCiudad(idCiudad);
    }

}