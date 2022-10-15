package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    // CRUD ------------------------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crear() {
        Cliente cliente = new Cliente("1223", "Juan Perez", "juanp@mail.com", "a123");

        cliente.agregarTelefono("322455", "Celular");
        cliente.agregarTelefono("78890", "Fijo");

        Cliente guardado = clienteRepo.save(cliente);

        // Verificar si la prueba se paso o no
        Assertions.assertNotNull(guardado);
        Assertions.assertEquals("Juan Perez", guardado.getNombreCompleto());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {
        Cliente guardado = clienteRepo.findById("9876").orElse(null);

        assert guardado != null;
        clienteRepo.delete(guardado);

        Optional<Cliente> borrado = clienteRepo.findById("9876");

        // Verificacion
        Assertions.assertNull( borrado.orElse(null) );
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar () {
        Cliente guardado = clienteRepo.findById("1223").orElse(null);

        assert guardado != null;
        guardado.setNombreCompleto("Pedro Jimenez");

        Cliente nuevo = clienteRepo.save(guardado);

        // Verificacion
        Assertions.assertEquals("Pedro Jimenez", nuevo.getNombreCompleto());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {
        // Cliente buscado: Ivor Randolph
        Optional<Cliente> buscado = clienteRepo.findById("2345");

        Assertions.assertNotNull( buscado.orElse(null) );
        System.out.println( buscado.orElse(null) );
    }

    // Otras consultas -----------------------------------------------------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {
        List<Cliente> clientes = clienteRepo.findAll();
        System.out.println("");
        clientes.forEach(System.out::println);
        System.out.println("");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorCorreo() {
        Cliente cliente = clienteRepo.obtener("nisi@icloud.ca");
        Cliente cliente2 = clienteRepo.findByEmail("nisi@icloud.ca");

        Assertions.assertEquals( "2345", cliente.getCedula() );
        Assertions.assertEquals( "2345", cliente2.getCedula() );
        System.out.println(cliente);
        System.out.println(cliente2);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarInicioSesion() {
        Cliente cliente = clienteRepo.findByEmailAndContrasena("curabitur@google.couk", "e123");

        Assertions.assertNotNull(cliente);
        System.out.println(cliente);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCupones() {
        List<Cupon> cupones = clienteRepo.obtenerCupones("dictum.phasellus@aol.org");

        Assertions.assertNotNull(cupones);
        cupones.forEach(System.out::println);
    }
}
