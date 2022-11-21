package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    // Repositorios sobre los cuales se haran las consultas
    private final ClienteRepo clienteRepo;
    private final FuncionRepo funcionRepo;
    private final CuponRepo cuponRepo;
    private final CuponClienteRepo cuponClienteRepo;
    private final CompraRepo compraRepo;
    private final EntradaRepo entradaRepo;
    private final CiudadRepo ciudadRepo;
    private final SalaRepo salaRepo;
    private final CompraConfiteriaRepo compraConfiteriaRepo;

    // Servicio de email
    private final EmailServicio emailServicio;

    public ClienteServicioImpl(ClienteRepo clienteRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo,
                               CuponClienteRepo cuponClienteRepo, CompraRepo compraRepo, EmailServicio emailServicio,
                               EntradaRepo entradaRepo, CiudadRepo ciudadRepo, SalaRepo salaRepo, CompraConfiteriaRepo compraConfiteriaRepo) {
        this.clienteRepo = clienteRepo;
        this.funcionRepo = funcionRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.compraRepo = compraRepo;
        this.emailServicio = emailServicio;
        this.entradaRepo = entradaRepo;
        this.ciudadRepo = ciudadRepo;
        this.salaRepo = salaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
    }

    @Override
    public Cliente iniciarSesion(String email, String contrasena) throws Exception {

        if( email == null || email.equals("") || contrasena == null || contrasena.equals("") )
            throw new Exception("Datos incompletos");

        Cliente clienteEncontrado = clienteRepo.findByEmail(email);

        if (clienteEncontrado == null) throw new Exception("El correo ingresado no existe");

        if( clienteEncontrado.getEstado() == EstadoPersona.INACTIVO )
            throw new Exception("Cliente inactivo. Por favor active su cuenta a traves del enlace enviado a su correo electronico");

        // Comprobar contraseña encriptada
        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();

        if( !spe.checkPassword(contrasena, clienteEncontrado.getContrasena() ) )
            throw new Exception("La contraseña es incorrecta");

        if( clienteEncontrado.getFechaNacimiento() != null && hoyCumpleAnios(clienteEncontrado) ) {
            enviarCuponCumpleanios(clienteEncontrado);
            clienteEncontrado.agregarAnioCelebrado( LocalDate.now().getYear() );
            clienteRepo.save(clienteEncontrado);
        }

        return clienteEncontrado;
    }

    private void enviarCuponCumpleanios(Cliente cliente) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("heisenberg");

        String param1 = textEncryptor.encrypt("H-Bday");
        String param2 = textEncryptor.encrypt( cliente.getEmail() );

        String mensaje = "¡Unicine te desea un feliz cumpleaños! Disfrutalo con un cupon de regalo " +
                "con el que puedes obtener un 15% de descuento del valor total de cualquier compra! " +
                "Recibelo a traves del siguiente enlace: " +
                "http://localhost:8080/agregar_cupon.xhtml?p1=" + param1 + "&p2=" + param2;

        emailServicio.enviarEmail("Cupon de Cumpleaños - 15% Descuento", mensaje, cliente.getEmail());
    }

    private boolean hoyCumpleAnios(Cliente cliente) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimientoCliente = cliente.getFechaNacimiento();

        if( cliente.getAniosCelebrados().contains(fechaActual.getYear()) )
            return false;

        return fechaActual.getMonth()      == fechaNacimientoCliente.getMonth() &&
               fechaActual.getDayOfMonth() == fechaNacimientoCliente.getDayOfMonth();
    }

    @Override
    public Cliente registrar(Cliente cliente) throws Exception {
        if(cliente == null) throw new Exception("No hay cliente para registrar");

        if ( emailExiste(cliente.getEmail()) )
            throw new Exception("El correo que intenta registrar ya existe");

        // Encriptacion de contraseña
        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
        cliente.setContrasena( spe.encryptPassword( cliente.getContrasena() ) );

        // Setteo del estado inactivo para mayor seguridad
        cliente.setEstado(EstadoPersona.INACTIVO);

        Cliente guardado = clienteRepo.save(cliente);

        enviarConfirmacion(guardado);

        return guardado;
    }

    private boolean emailExiste(String email) {
        return clienteRepo.findByEmail(email) != null;
    }

    private void enviarConfirmacion(Cliente cliente) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("heisenberg");

        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Bogota"));

        String param1 = textEncryptor.encrypt( cliente.getEmail() );
        String param2 = textEncryptor.encrypt( "" + zdt.toInstant().toEpochMilli() );

        String mensaje = "¡Hola, " + cliente.getNombreCompleto() + "! Acaba de crear su cuenta en Unicine. " +
                "Antes de hacer uso de nuestros servicios, por favor confirme su correo electronico a traves del siguiente enlace: " +
                "http://localhost:8080/activar_cuenta.xhtml?p1=" + param1 + "&p2=" + param2;

        emailServicio.enviarEmail("Confirmacion E-mail Unicine", mensaje, cliente.getEmail());
    }

    private void enviarCuponBienvenida(Cliente cliente) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("heisenberg");

        String param1 = textEncryptor.encrypt("Hola Mundo");
        String param2 = textEncryptor.encrypt( cliente.getEmail() );

        String mensaje = "¡Disfruta ahora mismo un cupon de bienvenida totalmente gratis " +
                "con el que puedes obtener un 15% de descuento del valor total de cualquier compra! " +
                "Recibelo a traves del siguiente enlace: " +
                "http://localhost:8080/agregar_cupon.xhtml?p1=" + param1 + "&p2=" + param2;

        emailServicio.enviarEmail("Cupon de Bienvenida - 15% Descuento", mensaje, cliente.getEmail());
    }

    @Override
    public Cliente activarCuenta(String email, String fecha) throws Exception {
        email = email.replaceAll(" ", "+");
        fecha = fecha.replaceAll(" ", "+");

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("heisenberg");

        String emailDesencriptado = textEncryptor.decrypt(email);
        String fechaDescencriptada = textEncryptor.decrypt(fecha);

        Cliente clienteEncontrado = clienteRepo.findByEmail(emailDesencriptado);

        if(clienteEncontrado == null) throw new Exception("El cliente no existe");

        if(clienteEncontrado.getEstado() == EstadoPersona.ACTIVO)
            throw new Exception("Esta cuenta ya esta verificada");

        clienteEncontrado.setEstado(EstadoPersona.ACTIVO);
        clienteRepo.save(clienteEncontrado);

        enviarCuponBienvenida(clienteEncontrado);

        return clienteEncontrado;
    }

    @Override
    public Cliente actualizar(Cliente cliente) throws Exception {
        if(cliente == null) throw new Exception("No hay cliente para actualizar");

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cliente.getCedula());

        if (clienteGuardado.isEmpty()) throw new Exception("Cliente no encontrado");

        if( !cliente.getEmail().equals(clienteGuardado.get().getEmail() ) && emailExiste(cliente.getEmail()) )
            throw new Exception("El correo que intenta actualizar ya existe");

        if( !clienteGuardado.get().getContrasena().equals(cliente.getContrasena()) ) {
            // Encriptacion de contraseña
            StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
            cliente.setContrasena( spe.encryptPassword( cliente.getContrasena() ) );
        }

        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminar(String cedulaCliente) throws Exception {
        if(cedulaCliente == null || cedulaCliente.equals("")) throw new Exception("Cedula del cliente vacia");

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedulaCliente);

        if (clienteGuardado.isEmpty()) throw new Exception("El cliente no existe");

        clienteRepo.delete(clienteGuardado.get());
    }

    @Override
    public Cliente obtener(String cedulaCliente) throws Exception {
        if(cedulaCliente == null || cedulaCliente.equals("")) throw new Exception("Cedula del cliente vacia");

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedulaCliente);

        if (clienteGuardado.isEmpty()) throw new Exception("Cliente no encontrado");

        return clienteGuardado.get();
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public List<Compra> listarCompras(String cedulaCliente) throws Exception {
        if(cedulaCliente == null || cedulaCliente.equals("")) throw new Exception("Cedula del cliente vacia");

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedulaCliente);

        if(clienteGuardado.isEmpty()) throw new Exception("Cliente no encontrado");

        return clienteRepo.obtenerCompras(cedulaCliente);
    }

    @Override
    public Compra redimirCupon(Integer idCuponCliente, Compra compra) throws Exception {

        if(idCuponCliente == null || idCuponCliente.equals(0)) throw new Exception("ID de cupon invalido");
        if(compra == null) throw new Exception("No hay compra en la cual redimir el cupon");

        CuponCliente cuponRedimido = cuponClienteRepo.findById(idCuponCliente).orElse(null);

        if( cuponRedimido == null ) throw new Exception("El cupon ingresado no se encuentra en la base de datos");

        if( cuponVencido(cuponRedimido.getCupon()) )
            throw new Exception("El cupon ingresado no se encuentra disponible");

        // Actualizar estado del cupon
        cuponRedimido.setEstado(EstadoCupon.USADO);
        cuponClienteRepo.save(cuponRedimido);

        // Agregar el cupon a la compra
        compra.setCuponCliente(cuponRedimido);

        return compra;
    }

    @Override
    public CuponCliente obtenerCuponCliente(Integer idCuponCliente) throws Exception {
        if(idCuponCliente == null || idCuponCliente.equals(0)) throw new Exception("ID de cupon vacio");

        Optional<CuponCliente> cuponClienteGuardado = cuponClienteRepo.findById(idCuponCliente);

        if (cuponClienteGuardado.isEmpty()) throw new Exception("Cupon no encontrado");

        return cuponClienteGuardado.get();
    }


    @Override
    public List<Pelicula> buscarPeliculas(String busqueda, Integer idCiudad) throws Exception {
        if(busqueda == null || busqueda.equals("")) throw new Exception("Busqueda vacia");
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("Ciudad vacia");

        return funcionRepo.buscarPeliculas(busqueda, idCiudad);
    }

    @Override
    public Compra registrarCompra(Compra compra) throws Exception {
        if(compra == null) throw new Exception("No hay compra para registrar");

        compra.setFechaCompra( LocalDateTime.now() );
        compra.calcularValorTotal();

        compraRepo.save(compra);
        entradaRepo.saveAll( compra.getEntradas() );
        compraConfiteriaRepo.saveAll(compra.getComprasConfiteria() );

        if( primeraCompra(compra.getCliente()) ) enviarCuponPrimeraCompra(compra.getCliente());

        enviarConfirmacionCompra(compra.getCliente(), compra);

        return compra;
    }

    private boolean primeraCompra(Cliente cliente) {
        return clienteRepo.obtenerCompras(cliente.getCedula()).size() <= 1;
    }

    private void enviarCuponPrimeraCompra(Cliente cliente) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("heisenberg");

        String param1 = textEncryptor.encrypt("F.I.R.S.T.");
        String param2 = textEncryptor.encrypt( cliente.getEmail() );

        String mensaje = "Felicidades por tu primera compra! Para que sigas disfrutando del mejor cine te hemos enviado un cupon de regalo " +
                "con el que puedes obtener un 10% de descuento del valor total de cualquier compra! " +
                "Recibelo a traves del siguiente enlace: " +
                "http://localhost:8080/agregar_cupon.xhtml?p1=" + param1 + "&p2=" + param2;

        emailServicio.enviarEmail("Cupon de Regalo - 10% Descuento", mensaje, cliente.getEmail());
    }

    private void enviarConfirmacionCompra(Cliente cliente, Compra compra) {
        String mensaje = "¡Has realizado una compra!     " +
                         " | ID de la compra: " + compra.getId() +
                         " | Cantidad de entradas: " + compra.getEntradas().size() +
                         " | Subtotal entradas: $" + compra.obtenerTotalEntradas() +
                         " | Pelicula: " + compra.getFuncion().getPelicula().getNombre() +
                         " | Sala: " + compra.getFuncion().getSala().getNumero() +
                         " | Teatro: " + compra.getFuncion().getSala().getTeatro().getNombre() + " " + compra.getFuncion().getSala().getTeatro().getCiudad().getNombre() +
                         " | Direccion: " + compra.getFuncion().getSala().getTeatro().getDireccion() +
                         " | Subtotal confiteria: $" + compra.obtenerTotalConfiteria() +
                         " | Fecha y hora de la funcion: " + compra.getFuncion().getHorario().getFecha().toString() + " " + compra.getFuncion().getHorario().getHora().toString() + "\n" +
                         " || VALOR TOTAL: $" + compra.getValorTotal() + " ||      " +
                         " ----------------------- " +
                         " Puedes verla en tu historial de compras: http://localhost:8080/cliente/historial_compras.xhtml";

        emailServicio.enviarEmail("Confirmacion Compra", mensaje, cliente.getEmail());
    }

    private boolean clienteInactivo(String cedulaCliente) {
        return clienteRepo.obtenerEstadoCliente(cedulaCliente).equals(EstadoPersona.INACTIVO);
    }

    @Override
    public void enviarCorreoCambioContrasena(String emailCliente) throws Exception {
        Cliente cliente = clienteRepo.findByEmail(emailCliente);

        if(cliente == null) throw new Exception("Cliente no encontrado");

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("heisenberg");

        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Bogota"));

        String param1 = textEncryptor.encrypt( emailCliente );
        String param2 = textEncryptor.encrypt( "" + zdt.toInstant().toEpochMilli() );
        String mensaje = "Ha solicitado cambiar su contraseña. Para hacerlo haga clic en el siguiente enlace: " +
                         "http://localhost:8080/cambiar_contrasena.xhtml?p1=" + param1 + "&p2=" + param2;;

        emailServicio.enviarEmail("Cambio de Contraseña", mensaje, emailCliente);
    }

    @Override
    public void cambiarContrasena(String email, String fecha, String nuevaContrasena) throws Exception {
        email = email.replaceAll(" ", "+");
        fecha = fecha.replaceAll(" ", "+");

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("heisenberg");

        String emailDesencriptado = textEncryptor.decrypt(email);
        String fechaDescencriptada = textEncryptor.decrypt(fecha);

        Cliente clienteEncontrado = clienteRepo.findByEmail(emailDesencriptado);

        if(clienteEncontrado == null) throw new Exception("El cliente no existe");

        if(nuevaContrasena == null || nuevaContrasena.isEmpty()) throw new Exception("No se ingreso una nueva contraseña");

        // Encriptacion de contraseña
        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
        clienteEncontrado.setContrasena( spe.encryptPassword(nuevaContrasena) );
        clienteRepo.save(clienteEncontrado);
    }

    @Override
    public CuponCliente agregarCupon(String nombreCupon, Cliente cliente) throws Exception {
        CuponCliente cuponCliente;
        Cupon cupon;

        if(nombreCupon == null || nombreCupon.equals("")) throw new Exception("Nombre de cupon vacio");
        if(cliente == null) throw new Exception("No hay cliente al cual asignar el cupon");
        if( !clienteRepo.existsById(cliente.getCedula()) )
            throw new Exception("El cliente no existe en la base de datos");

        cupon = cuponRepo.findByNombre(nombreCupon);

        if( cupon != null && !cuponVencido(cupon) ) {
            cuponCliente = new CuponCliente(cliente, cupon);
            cuponClienteRepo.save(cuponCliente);
        }
        else {
            throw new Exception("El cupon no esta disponible o esta vencido");
        }

        return cuponCliente;
    }

    @Override
    public void agregarCuponCorreo(String nombreCupon, String emailCliente) throws Exception {
        nombreCupon = nombreCupon.replaceAll(" ", "+");
        emailCliente = emailCliente.replaceAll(" ", "+");

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("heisenberg");

        String nombreCuponDesencriptado = textEncryptor.decrypt(nombreCupon);
        String emailDesencriptado = textEncryptor.decrypt(emailCliente);

        Cupon cuponEncontrado = cuponRepo.findByNombre(nombreCuponDesencriptado);
        Cliente clienteEncontrado = clienteRepo.findByEmail(emailDesencriptado);

        if(cuponEncontrado == null) throw new Exception("El cupon no existe");
        if(clienteEncontrado == null) throw new Exception("El cliente no existe");

        CuponCliente cuponCliente = new CuponCliente(clienteEncontrado, cuponEncontrado);

        try {
            CuponCliente repetido = cuponClienteRepo.findByClienteAndCupon(clienteEncontrado, cuponEncontrado);
        }
        catch(IncorrectResultSizeDataAccessException e) {
            throw new Exception("Ya tienes este cupon");
        }

        cuponClienteRepo.save(cuponCliente);
    }

    @Override
    public boolean cuponVencido(Cupon cupon) {
        return cupon.getFechaVencimiento().isBefore( LocalDate.now() ) || cupon.getFechaVencimiento().isEqual( LocalDate.now() );
    }

    @Override
    public List<Ciudad> obtenerCiudades() {
        return ciudadRepo.obtenerCiudades();
    }

    @Override
    public Ciudad obtenerCiudad(Integer idCiudad) throws Exception {
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("id de la ciudad vacio");
        return ciudadRepo.findById(idCiudad).orElse(null);
    }

    @Override
    public List<Entrada> obtenerEntradasCompradas(Funcion funcion) throws Exception {
        if(funcion == null) throw new Exception("No hay funcion de la cual obtener entradas compradas");
        if( !funcionRepo.existsById(funcion.getId()) )
            throw new Exception("La funcion no existe en la base de datos");

        return entradaRepo.obtenerEntradasCompradasFuncion(funcion.getId());
    }

    @Override
    public List<Pelicula> filtrarPeliculasEstadoCiudad(Integer idCiudad, EstadoPelicula estado) throws Exception {
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("id de la ciudad vacio");

        List<Sala> salas = ciudadRepo.obtenerSalasCiudad(idCiudad);

        if(salas.isEmpty()) throw new Exception("La ciudad no tiene salas de cine");

        List<Pelicula> peliculas = new ArrayList<>();

        for (Sala s: salas) {
            peliculas.addAll( salaRepo.obtenerFuncionesSalaEstado(s.getId(), estado) );
        }

        return peliculas;
    }

    @Override
    public List<Funcion> obtenerFuncionesPelicula(Integer idPelicula) throws Exception {
        if(idPelicula == null || idPelicula.equals(0)) throw new Exception("ID de pelicula vacio");

        return funcionRepo.obtenerFuncionesPelicula(idPelicula);
    }

    @Override
    public List<CuponCliente> obtenerCuponesCliente(Cliente cliente) throws Exception {
        if(cliente == null) throw new Exception("Cliente vacio");

        return cuponClienteRepo.obtenerCuponesCliente(cliente.getCedula());
    }

    @Override
    public List<CuponCliente> obtenerCuponesClienteEstado(Cliente cliente, EstadoCupon estadoCupon) throws Exception {
        if(cliente == null) throw new Exception("Cliente vacio");
        if(estadoCupon == null) throw new Exception("Estado del cupon vacio");
        return cuponClienteRepo.obtenerCuponesClienteEstado(cliente.getCedula(), estadoCupon);
    }

    @Override
    public CuponCliente actualizarCuponCliente(CuponCliente cuponCliente) throws Exception {
        if(cuponCliente == null) throw new Exception("Cupon vacio");

        Optional<CuponCliente> cuponClienteGuardado = cuponClienteRepo.findById(cuponCliente.getId());

        if (cuponClienteGuardado.isEmpty()) throw new Exception("Cupon no asociado con ningun cliente");

        return cuponClienteRepo.save(cuponCliente);
    }

    @Override
    public Compra obtenerCompra(Integer idCompra) throws Exception {
        if(idCompra == null || idCompra.equals(0)) throw new Exception("ID de compra vacio");

        return compraRepo.findById(idCompra).orElse(null);
    }

}
