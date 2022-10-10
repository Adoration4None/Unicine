package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    // Repositorio sobre el cual se haran las consultas
    private final ClienteRepo clienteRepo;

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
        return null;
    }

    @Override
    public boolean redimirCupon(Integer idCupon) throws Exception {
        return false;
    }
}
