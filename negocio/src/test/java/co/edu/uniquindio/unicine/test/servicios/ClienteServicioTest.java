package co.edu.uniquindio.unicine.test.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.MetodoPago;
import co.edu.uniquindio.unicine.repo.CompraRepo;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.EmailServicio;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private CuponRepo cuponRepo;

    public void iniciarSesion() {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        Cliente cliente = Cliente.builder().cedula("99775").nombreCompleto("Fernando Perez").email("fer.perez@mail.com").contrasena("0987654321").build();

        try {
            Cliente nuevo = clienteServicio.registrar(cliente);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        try {
            clienteServicio.eliminar("2345");

            Cliente clientePrueba = clienteServicio.obtener("2345");
            Assertions.assertNull(clientePrueba);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        try {
            Cliente cliente = clienteServicio.obtener("9876");
            cliente.setNombreCompleto("Camila Rodriguez");

            Cliente actualizado = clienteServicio.actualizar(cliente);
            Assertions.assertEquals("Camila Rodriguez", actualizado.getNombreCompleto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClientes() {
        List<Cliente> clientes = clienteServicio.listarClientes();

        Assertions.assertEquals(5, clientes.size() );
        clientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCompras() {

    }

    // ¿COMO PROBAR ESTO?
    @Test
    @Sql("classpath:dataset.sql")
    public void redimirCupon() {
        Compra compraInicial = new Compra();

        Compra compra = new Compra();

        if(compraInicial != null) {
            try {
                compra = clienteServicio.redimirCupon(1, compraInicial);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Assertions.assertEquals(1, compra.getCupon().getId());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarContrasena() {
        boolean resultado = false;

        try {
            resultado = clienteServicio.cambiarContrasena("nisi@icloud.ca");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(resultado);
    }

    @Test
    public void enviarEmailTest() {
        emailServicio.enviarEmail("Primera prueba", "Esto es un mensaje de prueba", "samuel.echeverrib@uqvirtual.edu.co");
    }
}
