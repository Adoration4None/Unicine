package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import co.edu.uniquindio.unicine.repo.CompraRepo;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    // Repositorios sobre los cuales se haran las consultas
    private final ClienteRepo clienteRepo;
    private final PeliculaRepo peliculaRepo;
    private final CuponRepo cuponRepo;
    private final CompraRepo compraRepo;

    // Servicio de email
    private final EmailServicio emailServicio;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, CuponRepo cuponRepo,
                               CompraRepo compraRepo, EmailServicio emailServicio) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.cuponRepo = cuponRepo;
        this.compraRepo = compraRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public Cliente iniciarSesion(String email, String contrasena) throws Exception {
        Cliente clienteEncontrado = clienteRepo.findByEmailAndContrasena(email, contrasena);

        if (clienteEncontrado == null) {
            throw new Exception("Datos de autenticacion incorrectos");
        }

        return clienteEncontrado;
    }

    @Override
    public Cliente registrar(Cliente cliente) throws Exception {

        if (emailExiste(cliente.getEmail())) {
            throw new Exception("El correo que intenta registrar ya existe");
        }

        return clienteRepo.save(cliente);
    }

    private boolean emailExiste(String email) {
        return clienteRepo.findByEmail(email) != null;
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

        if( !cuponRepo.validarCupon(idCupon) ) {
            throw new Exception("El cupon no se puede redimir. Por favor revise si aun se encuentra disponible");
        }

        // Actualizar estado del cupon
        Cupon cuponRedimido = cuponRepo.findById(idCupon).orElse(null);

        if (cuponRedimido != null) {
            cuponRedimido.setEstado(EstadoCupon.USADO);
            cuponRepo.save(cuponRedimido);

            // Agregar el cupon a la compra
            compra.setCupon(cuponRedimido);
        }

        return compra;
    }

    @Override
    public List<Pelicula> buscarPeliculas(String busqueda) throws Exception {
        if(busqueda != null && !busqueda.equals("")) return peliculaRepo.buscarPelicula(busqueda);
        else throw new Exception("Busqueda vacia");
    }

    @Override
    public Compra realizarCompra(Compra compra, Cliente cliente) throws Exception {
        Optional<Cliente> clienteGuardado = clienteRepo.findById(cliente.getCedula());
        //cliente.addCompra();
        return null;
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
        return null;
    }


}
