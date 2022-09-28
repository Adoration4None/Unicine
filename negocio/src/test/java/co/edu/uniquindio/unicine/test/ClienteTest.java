package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void crear() {
        Cliente cliente = new Cliente("1223", "Juan Perez", "juanp@mail.com", "a123");

        cliente.agregarTelefono("Celular", "322455");
        cliente.agregarTelefono("Fijo", "78890");

        Cliente guardado = clienteRepo.save(cliente);

        // Verificar si la prueba se paso o no
        Assertions.assertNotNull(guardado);
        Assertions.assertEquals("Juan Perez", guardado.getNombreCompleto());

    }

    @Test
    public void eliminar() {
        Cliente cliente = new Cliente("1223", "Juan Perez", "juanp@mail.com", "a123");

        cliente.agregarTelefono("Celular", "322455");
        cliente.agregarTelefono("Fijo", "78890");

        Cliente guardado = clienteRepo.save(cliente);

        clienteRepo.delete(guardado);

        Optional<Cliente> borrado = clienteRepo.findById("1223");

        // Verificacion
        Assertions.assertNull( borrado.orElse(null) );
    }

    @Test
    public void actualizar () {
        Cliente cliente = new Cliente("1223", "Juan Perez", "juanp@mail.com", "a123");

        cliente.agregarTelefono("Celular", "322455");
        cliente.agregarTelefono("Fijo", "78890");

        Cliente guardado = clienteRepo.save(cliente);

        guardado.setNombreCompleto("Pedro Jimenez");

        Cliente nuevo = clienteRepo.save(guardado);

        // Verificacion
        Assertions.assertEquals("Pedro Jimenez", nuevo.getNombreCompleto());
    }

    @Test
    public void obtener() {
        Cliente cliente = new Cliente("1223", "Juan Perez", "juanp@mail.com", "a123");

        cliente.agregarTelefono("Celular", "322455");
        cliente.agregarTelefono("Fijo", "78890");

        clienteRepo.save(cliente);

        Optional<Cliente> buscado = clienteRepo.findById("1223");

        System.out.println( buscado.orElse(null) );
    }

    @Test
    public void listar() {
        Cliente cliente = new Cliente("1223", "Juan Perez", "juanp@mail.com", "a123");
        cliente.agregarTelefono("Celular", "322455");
        cliente.agregarTelefono("Fijo", "78890");

        Cliente cliente2 = new Cliente("544", "Maria Ramirez", "maria@mail.es", "hola");

        clienteRepo.save(cliente);
        clienteRepo.save(cliente2);

        List<Cliente> clientes = clienteRepo.findAll();

        System.out.println(clientes);
    }
}
