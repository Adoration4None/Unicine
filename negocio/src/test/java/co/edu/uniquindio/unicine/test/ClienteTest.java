package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
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
        Assertions.assertEquals("Jun Perez", guardado.getNombreCompleto());

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {
        String[] tels = new String[] {"322", "896"};
        Cliente cliente = new Cliente ( "Pepito", "pepe@", "1234", "ruta", Arrays.asList(tels));
        cliente.setCedula("1234567890");

        Cliente guardado = clienteRepo.save(cliente);

        clienteRepo.delete(guardado);
        Cliente buscado = clienteRepo.findById("1234567890");

        Optional<Cliente> buscado = clienteRepo.findById("1234567890");

        Assertions.assertNull(buscado.orElse(null));

    }

    public void actualizar () {

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {
        Optional<Cliente> buscado = clienteRepo.findById(4);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    public void listar() {

    }
}
