package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

    public void eliminar() {

    }

    public void actualizar () {

    }

    public void obtener() {

    }

    public void listar() {

    }
}
