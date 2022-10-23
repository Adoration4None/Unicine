package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    // Repositorios sobre los cuales se haran las consultas
    private final ClienteRepo clienteRepo;
    private final PeliculaRepo peliculaRepo;
    private final CuponRepo cuponRepo;
    private final CompraRepo compraRepo;
    private final EntradaRepo entradaRepo;

    // Servicio de email
    private final EmailServicio emailServicio;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, CuponRepo cuponRepo,
                               CompraRepo compraRepo, EmailServicio emailServicio, EntradaRepo entradaRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.cuponRepo = cuponRepo;
        this.compraRepo = compraRepo;
        this.emailServicio = emailServicio;
        this.entradaRepo = entradaRepo;
    }

    @Override
    public Cliente iniciarSesion(String email, String contrasena) throws Exception {

        if( (email == null || email.equals("")) || (contrasena == null || contrasena.equals("")) )
            throw new Exception("Datos incompletos");

        Cliente clienteEncontrado = clienteRepo.findByEmailAndContrasena(email, contrasena);

        if (clienteEncontrado == null) throw new Exception("Datos de autenticacion incorrectos");

        return clienteEncontrado;
    }

    @Override
    public Cliente registrar(Cliente cliente) throws Exception {

        if(cliente == null) throw new Exception("No hay cliente para registrar");

        if ( emailExiste(cliente.getEmail()) )
            throw new Exception("El correo que intenta registrar ya existe");

        Cliente guardado = clienteRepo.save(cliente);

        enviarConfirmacion(guardado);
        enviarCuponBienvenida(guardado);

        return guardado;
    }

    private boolean emailExiste(String email) {
        return clienteRepo.findByEmail(email) != null;
    }

    private void enviarConfirmacion(Cliente cliente) throws Exception {
        String mensaje = "Hola, " + cliente.getNombreCompleto() + "! Acaba de crear su cuenta en Unicine. " +
                "Antes de hacer uso de nuestros, por favor confirme su correo electronico a traves del siguiente enlace: " +
                "https://bit.ly/3s7ETPZ";

        emailServicio.enviarEmail("Confirmacion E-mail Unicine", mensaje, cliente.getEmail());
    }

    private void enviarCuponBienvenida(Cliente cliente) {
        String mensaje = "Disfruta ahora mismo un cupon de bienvenida totalmente gratis " +
                "con el que puedes obtener un 15% de descuento del valor total de cualquier compra! " +
                "Recibelo a traves del siguiente enlace: " +
                "https://bit.ly/3s7ETPZ";

        emailServicio.enviarEmail("Cupon de Bienvenida - 15% Descuento", mensaje, cliente.getEmail());
    }

    @Override
    public Cliente activarCuenta(Cliente cliente) throws Exception {

        if(cliente == null) throw new Exception("No hay cliente al cual activar cuenta");
        if( !emailExiste(cliente.getEmail()) ) throw new Exception("El cliente no existe en la base de datos");

        cliente.setEstado(EstadoPersona.ACTIVO);
        clienteRepo.save(cliente);

        return cliente;
    }

    @Override
    public Cliente actualizar(Cliente cliente) throws Exception {
        Optional<Cliente> clienteGuardado = clienteRepo.findById(cliente.getCedula());

        if (clienteGuardado.isEmpty()) throw new Exception("Cliente no encontrado");
        if( emailExiste(cliente.getEmail()) ) throw new Exception("El correo que intenta actualizar ya existe");

        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminar(String cedulaCliente) throws Exception {
        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedulaCliente);

        if (clienteGuardado.isEmpty()) throw new Exception("El cliente no existe");

        clienteRepo.delete(clienteGuardado.get());
    }

    @Override
    public Cliente obtener(String cedulaCliente) throws Exception {
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
        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedulaCliente);

        if(clienteGuardado.isEmpty()) throw new Exception("Cliente no encontrado");

        return clienteRepo.obtenerCompras(cedulaCliente);
    }

    @Override
    public Compra redimirCupon(Integer idCupon, Compra compra) throws Exception {

        if(compra == null) throw new Exception("No hay compra en la cual redimir el cupon");
        if( compraNoExiste(compra.getId()) ) throw new Exception("La compra no existe en la base de datos");

        if( !cuponRepo.validarCupon(idCupon) ) {
            throw new Exception("El cupon ingresado no existe o ya no se encuentra disponible");
        }

        Cupon cuponRedimido = cuponRepo.findById(idCupon).orElse(null);

        if (cuponRedimido != null) {
            // Actualizar estado del cupon
            cuponRedimido.setEstado(EstadoCupon.USADO);
            cuponRedimido.getCompras().add(compra);
            cuponRepo.save(cuponRedimido);

            // Agregar el cupon a la compra
            compra.setCupon(cuponRedimido);
        }

        return compra;
    }

    @Override
    public List<Pelicula> buscarPeliculas(String busqueda) throws Exception {
        if(busqueda == null || busqueda.equals("")) throw new Exception("Busqueda vacia");

        return peliculaRepo.buscarPeliculas(busqueda);
    }

    public Compra iniciarCompra(Cliente cliente, Funcion funcion) throws Exception {

        if(cliente == null || funcion == null)
            throw new Exception("Se necesitan un cliente y una funcion para iniciar una compra");

        if( clienteInactivo(cliente.getCedula()) )
            throw new Exception("El cliente debe activar su cuenta para poder comprar");

        Compra compraNueva = Compra.builder().cliente(cliente).funcion(funcion).build();
        compraRepo.save(compraNueva);

        return compraNueva;
    }

    @Override
    public Compra asignarAsientosCompra(Compra compra, List<Entrada> entradas) throws Exception {

        if(compra == null) throw new Exception("No hay compra a la cual asignar asientos");

        if(entradas == null || entradas.size() < 1)
            throw new Exception("No se han seleccionado entradas para comprar");

        if( compraNoExiste(compra.getId()) ) throw new Exception("La compra no existe en la base de datos");

        compra.setEntradas(entradas);
        asignarCompraEntradas(compra, entradas);

        compraRepo.save(compra);

        return compra;
    }

    private void asignarCompraEntradas(Compra compra, List<Entrada> entradas) {
        entradas.forEach(entrada -> {
            entrada.setCompra(compra);
            entradaRepo.save(entrada);
        } );
    }

    private boolean compraNoExiste(Integer id) { return compraRepo.findById(id).isEmpty(); }

    @Override
    public Compra comprarConfiteria(Compra compra, List<CompraConfiteria> confiteria) throws Exception {

        if(compra == null) throw new Exception("No hay compra a la cual asignar confiteria");

        if(confiteria == null || confiteria.size() < 1)
            throw new Exception("No se ha seleccionado confiteria para comprar");

        if( compraNoExiste(compra.getId()) ) throw new Exception("La compra no existe en la base de datos");

        compra.setComprasConfiteria(confiteria);
        compraRepo.save(compra);

        return compra;
    }

    @Override
    public Compra elegirMetodoPago(Compra compra, MetodoPago metodoPago) throws Exception {
        if(compra == null) throw new Exception("No hay compra a la cual asignar metodo de pago");
        if( metodoPago == null ) throw new Exception("No se ha seleccionado metodo de pago");
        if( compraNoExiste(compra.getId()) ) throw new Exception("La compra no existe en la base de datos");

        compra.setMetodoPago(metodoPago);
        compraRepo.save(compra);

        return compra;
    }

    @Override
    public Compra finalizarCompra(Compra compra, LocalDateTime fechaCompra) throws Exception {
        if(compra == null) throw new Exception("No hay compra para registrar");
        if( fechaCompra == null ) throw new Exception("No se ha brindado una fecha de compra");
        if( compraNoExiste(compra.getId()) ) throw new Exception("La compra no existe en la base de datos");

        compra.setFechaCompra(fechaCompra);
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
    public Cupon agregarCupon(String nombreCupon, Cliente cliente) throws Exception {
        Cupon cupon;

        if(nombreCupon == null || nombreCupon.equals("")) throw new Exception("Nombre de cupon vacio");

        if( nombreCuponValido(nombreCupon) ) {
            cupon = cuponRepo.findByNombre(nombreCupon);
            cupon.getClientes().add(cliente);
            cliente.getCupones().add(cupon);

            cuponRepo.save(cupon);
            clienteRepo.save(cliente);
        }
        else {
            throw new Exception("Cupon no valido");
        }

        return cupon;
    }

    private boolean nombreCuponValido(String nombreCupon) {
        return cuponRepo.findByNombre(nombreCupon) != null;
    }


}
