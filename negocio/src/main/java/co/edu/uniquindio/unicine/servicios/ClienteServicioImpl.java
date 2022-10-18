package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    // Repositorio sobre el cual se haran las consultas
    @Autowired
    private final ClienteRepo clienteRepo;
    @Autowired
    private PeliculaRepo peliculaRepo; //me marca pronlema al meterle el final por el constructor pero eso no tiene constructor, entonces no sé

    @Autowired
    private CuponRepo cuponRepo; //me marca pronlema al meterle el final por el constructor pero eso no tiene constructor, entonces no sé

    public ClienteServicioImpl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public Cliente iniciarSesion(String email, String contrasena) throws Exception {
        Cliente clienteEncontrado = clienteRepo.findByEmailAndContrasena(email, contrasena);

        if(clienteEncontrado == null) {
            throw new Exception("Datos de autenticacion incorrectos");
        }

        return clienteEncontrado;
    }

    @Override
    public Cliente registrar(Cliente cliente) throws Exception {

        if( emailExiste(cliente.getEmail()) ) {
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

        if( clienteGuardado.isEmpty() ) throw new Exception("El cliente no existe");

        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminar(String cedulaCliente) throws Exception {
        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedulaCliente);

        if( clienteGuardado.isEmpty() ) throw new Exception("El cliente no existe");

        clienteRepo.delete(clienteGuardado.get());
    }

    @Override
    public Cliente obtener(String cedulaCliente) throws Exception {
        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedulaCliente);

        if( clienteGuardado.isEmpty() ) throw new Exception("El cliente no existe");

        return clienteGuardado.get();
    }

    @Override
    public List<Cliente> listarClientes()  {
        return clienteRepo.findAll();
    }

    @Override
    public List<Compra> listarCompras(String cedulaCliente) throws Exception {
        return clienteRepo.obtenerCompras(cedulaCliente);
    }

    //redimir cupón qué es
    @Override
    public boolean redimirCupon(Integer idCupon, Integer idCompra) throws Exception {
        if(cuponRepo.validarCupon(idCupon)){
            //AQUÍ SERÍA SOLO MIRAR LA COMPRA DEL CLIENTE Y REDIMIR EL CUPON CON UNA FUNCION PERO NO SE
        }
        else{
            throw new Exception("El cupon no se puede redimir");
        }
    }

    @Override
    public List<Pelicula> buscarPeliculas(String busqueda) throws Exception {
        return peliculaRepo.buscarPelicula(busqueda);
    }

    @Override
    public Compra realizarCompra(Compra compra, String cedulaCliente) throws Exception {
        Optional<Cliente> cliente = clienteRepo.findById(cedulaCliente);
        //cliente.addCompra();
    }

    @Override
    public boolean cambiarContrasena(String contrasenaAnterior, String nuevaContrasena, String cedulaCliente) throws Exception {
        boolean flag = clienteRepo.validarContrasena(contrasenaAnterior).get();
        if(clienteRepo.validarContrasena(contrasenaAnterior)){
            Optional<Cliente> cliente = clienteRepo.findById(cedulaCliente);
            //cliente.actualizarContrasena(nuevaContrasena)
        }
        else{
            throw new Exception("Contraseña anterior incorrecta");
        }
    }
}
