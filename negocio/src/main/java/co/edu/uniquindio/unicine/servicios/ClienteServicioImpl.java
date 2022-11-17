package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
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

    // Servicio de email
    private final EmailServicio emailServicio;

    public ClienteServicioImpl(ClienteRepo clienteRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo,
                               CuponClienteRepo cuponClienteRepo, CompraRepo compraRepo, EmailServicio emailServicio,
                               EntradaRepo entradaRepo, CiudadRepo ciudadRepo, SalaRepo salaRepo) {
        this.clienteRepo = clienteRepo;
        this.funcionRepo = funcionRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.compraRepo = compraRepo;
        this.emailServicio = emailServicio;
        this.entradaRepo = entradaRepo;
        this.ciudadRepo = ciudadRepo;
        this.salaRepo = salaRepo;
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

        if( clienteEncontrado.getFechaNacimiento() != null && hoyCumpleAnios(clienteEncontrado) )
            enviarCuponCumpleanios(clienteEncontrado);

        return clienteEncontrado;
    }

    private void enviarCuponCumpleanios(Cliente cliente) {
        String mensaje = "¡Unicine te desea un feliz cumpleaños! Disfrutalo con un cupon de regalo " +
                "con el que puedes obtener un 15% de descuento del valor total de cualquier compra! " +
                "Recibelo a traves del siguiente enlace: " +
                "https://bit.ly/3s7ETPZ";

        emailServicio.enviarEmail("Cupon de Cumpleaños - 15% Descuento", mensaje, cliente.getEmail());
    }

    private boolean hoyCumpleAnios(Cliente cliente) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimientoCliente = cliente.getFechaNacimiento();

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
        String mensaje = "¡Disfruta ahora mismo un cupon de bienvenida totalmente gratis " +
                "con el que puedes obtener un 15% de descuento del valor total de cualquier compra! " +
                "Recibelo a traves del siguiente enlace: " +
                "https://bit.ly/3s7ETPZ";

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

        if( !cuponNoVencido(cuponRedimido.getCupon()) )
            throw new Exception("El cupon ingresado no se encuentra disponible");

        // Actualizar estado del cupon
        cuponRedimido.setEstado(EstadoCupon.USADO);
        cuponRedimido.setCompra(compra);
        cuponClienteRepo.save(cuponRedimido);

        // Agregar el cupon a la compra
        compra.setCuponCliente(cuponRedimido);

        return compra;
    }

    @Override
    public List<Pelicula> buscarPeliculas(String busqueda, Integer idCiudad) throws Exception {
        if(busqueda == null || busqueda.equals("")) throw new Exception("Busqueda vacia");
        if(idCiudad == null || idCiudad.equals(0)) throw new Exception("Ciudad vacia");

        return funcionRepo.buscarPeliculas(busqueda, idCiudad);
    }

    public Compra iniciarCompra(Cliente cliente, Funcion funcion) throws Exception {

        if(cliente == null || funcion == null)
            throw new Exception("Se necesitan un cliente y una funcion para iniciar una compra");

        if( clienteInactivo(cliente.getCedula()) )
            throw new Exception("El cliente debe activar su cuenta para poder comprar");

        return Compra.builder().cliente(cliente).funcion(funcion).build();
    }

    @Override
    public Compra asignarAsientosCompra(Compra compra, List<Entrada> entradas) throws Exception {

        if(compra == null) throw new Exception("No hay compra a la cual asignar asientos");
        if(entradas == null || entradas.isEmpty() ) throw new Exception("No se han seleccionado entradas para comprar");

        if( entradasNoDisponibles( compra.getFuncion(), entradas) )
            throw new Exception("Alguno(s) de los asientos ya esta(n) asignado(s)");

        if( !salaRepo.verificarAsientosDisponibles(entradas.size()) )
            throw new Exception("El numero de entradas excede la cantidad de sillas en la sala");

        compra.setEntradas(entradas);
        asignarCompraEntradas(compra, entradas);

        return compra;
    }

    private boolean entradasNoDisponibles(Funcion funcion, List<Entrada> entradas) {
        try {
            List<Entrada> entradasCompradas = obtenerEntradasCompradas(funcion);

            for(Entrada e : entradasCompradas) {
                if( entradas.contains(e) ) return true;
            }

            return false;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void asignarCompraEntradas(Compra compra, List<Entrada> entradas) {
        entradas.forEach(entrada -> {
            entrada.setCompra(compra);
            entradaRepo.save(entrada);
        } );
    }

    @Override
    public Compra comprarConfiteria(Compra compra, List<CompraConfiteria> confiteria) throws Exception {

        if(compra == null) throw new Exception("No hay compra a la cual asignar confiteria");

        if(confiteria == null || confiteria.size() < 1)
            throw new Exception("No se ha seleccionado confiteria para comprar");

        compra.setComprasConfiteria(confiteria);

        return compra;
    }

    @Override
    public Compra elegirMetodoPago(Compra compra, MetodoPago metodoPago) throws Exception {
        if(compra == null) throw new Exception("No hay compra a la cual asignar metodo de pago");
        if( metodoPago == null ) throw new Exception("No se ha seleccionado metodo de pago");

        compra.setMetodoPago(metodoPago);

        return compra;
    }

    @Override
    public Compra finalizarCompra(Compra compra) throws Exception {
        if(compra == null) throw new Exception("No hay compra para registrar");

        compra.setFechaCompra( LocalDateTime.now() );
        compra.calcularValorTotal();
        compraRepo.save(compra);

        Cliente cliente = compra.getCliente();
        cliente.getCompras().add(compra);
        clienteRepo.save(cliente);

        if( primeraCompra(compra.getCliente()) ) enviarCuponPrimeraCompra(compra.getCliente());

        enviarConfirmacionCompra(compra.getCliente(), compra);

        return compra;
    }

    private boolean primeraCompra(Cliente cliente) {
        return clienteRepo.obtenerCompras(cliente.getCedula()).size() <= 1;
    }

    private void enviarCuponPrimeraCompra(Cliente cliente) {
        String mensaje = "Felicidades por tu primera compra! Para conmemorar este momento, te hemos enviado un cupon de regalo " +
                "con el que puedes obtener un 10% de descuento del valor total de cualquier compra! " +
                "Recibelo a traves del siguiente enlace: " +
                "https://bit.ly/3s7ETPZ";

        emailServicio.enviarEmail("Cupon de Regalo - 10% Descuento", mensaje, cliente.getEmail());
    }

    private void enviarConfirmacionCompra(Cliente cliente, Compra compra) {
        String mensaje = "Has realizado una compra exitosa! Detalles: " +
                        compra.toString() + " \n " +
                        "Subtotal confiteria: " + compra.obtenerTotalConfiteria() + "\n" +
                        "Subtotal entradas: " + compra.obtenerTotalEntradas();

        emailServicio.enviarEmail("Confirmacion Compra", mensaje, cliente.getEmail());
    }

    private boolean clienteInactivo(String cedulaCliente) {
        return clienteRepo.obtenerEstadoCliente(cedulaCliente).equals(EstadoPersona.INACTIVO);
    }

    @Override
    public boolean cambiarContrasena(String emailCliente) throws Exception {
        Cliente cliente = clienteRepo.findByEmail(emailCliente);
        String mensaje = "Ha solicitado cambiar su contraseña. Para hacerlo haga clic en el siguiente enlace: " +
                         "https://bit.ly/3s7ETPZ";

        if(cliente == null){
            throw new Exception("Cliente no encontrado");
        }

        return emailServicio.enviarEmail("Cambio de Contraseña", mensaje, emailCliente);
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

        if( cupon != null && cuponNoVencido(cupon) ) {
            cuponCliente = new CuponCliente(cliente, cupon);
            cupon.agregarCuponCliente(cuponCliente);
            cliente.agregarCuponCliente(cuponCliente);

            cuponRepo.save(cupon);
            clienteRepo.save(cliente);
        }
        else {
            throw new Exception("Cupon no valido");
        }

        return cuponClienteRepo.save(cuponCliente);
    }

    private boolean cuponNoVencido(Cupon cupon) {
        return !cupon.getFechaVencimiento().equals( LocalDate.now() );
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
    public List<Funcion> obtenerFuncionesPelicula(String nombrePelicula) throws Exception {
        if(nombrePelicula == null || nombrePelicula.isEmpty()) throw new Exception("Pelicula vacia");

        return funcionRepo.obtenerFuncionesPelicula(nombrePelicula);
    }

}
