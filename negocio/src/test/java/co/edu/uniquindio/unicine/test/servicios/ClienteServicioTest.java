package co.edu.uniquindio.unicine.test.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.EmailServicio;
//import javafx.util.converter.LocalDateTimeStringConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private FuncionRepo funcionRepo;

    @Autowired
    private CuponRepo cuponRepo;

    @Autowired
    private EntradaRepo entradaRepo;

    @Autowired
    private ConfiteriaRepo confiteriaRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion() {
        try {
            Cliente clienteLogeado = clienteServicio.iniciarSesion("curabitur@google.couk", "e123");
            Assertions.assertEquals("Reagan Romero", clienteLogeado.getNombreCompleto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public void activarCuenta() {
        Cliente cliente = Cliente.builder().cedula("99775").nombreCompleto("Fernando Perez").email("fer.perez@mail.com").contrasena("0987654321").build();

        try {
            clienteServicio.registrar(cliente);
            Cliente activado = clienteServicio.activarCuenta(cliente);
            Assertions.assertNotNull(activado);
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
    public void obtener() {
        try {
            Cliente clienteEncontrado = clienteServicio.obtener("1235");
            Assertions.assertEquals("Ian Horn", clienteEncontrado.getNombreCompleto());
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
        try {
            List<Compra> comprasCliente = clienteServicio.listarCompras("1235");
            Assertions.assertEquals(2, comprasCliente.size());
            comprasCliente.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void redimirCupon() {
        Compra compraInicial = compraRepo.findById(4).orElse(null);

        try {
            Compra compra = clienteServicio.redimirCupon(2, compraInicial);
            Assertions.assertEquals(2, compra.getCuponCliente().getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarFunciones() {
        try {
            List<Funcion> funcionesEncontradas = clienteServicio.buscarPeliculas("a", 1);
            Assertions.assertNotNull(funcionesEncontradas);
            funcionesEncontradas.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarCompra() {
        Funcion funcion = funcionRepo.findById(5).orElse(null);

        try {
            Cliente cliente = clienteServicio.obtener("3344");
            Compra nuevaCompra = clienteServicio.iniciarCompra(cliente, funcion);

            Assertions.assertNotNull(nuevaCompra);
            System.out.println(nuevaCompra.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void asignarAsientosCompra() {
        Entrada entrada1 = entradaRepo.findById(6).orElse(null);
        Entrada entrada2 = entradaRepo.findById(7).orElse(null);
        List<Entrada> entradas = new ArrayList<>();
        entradas.add(entrada1);
        entradas.add(entrada2);

        Compra compraInicial = compraRepo.findById(1).orElse(null);

        try {
            Compra compra = clienteServicio.asignarAsientosCompra(compraInicial, entradas);
            Assertions.assertEquals(entradas, compra.getEntradas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void comprarConfiteria() {
        Compra compraInicial = compraRepo.findById(2).orElse(null);

        Confiteria comestible1 = confiteriaRepo.findById(2).orElse(null);
        Confiteria comestible2 = confiteriaRepo.findById(4).orElse(null);

        CompraConfiteria comestibleComprado1 = new CompraConfiteria();
        CompraConfiteria comestibleComprado2 = new CompraConfiteria();
        List<CompraConfiteria> confiteriaComprada = new ArrayList<>();

        if(comestible1 != null && comestible2 != null && compraInicial != null) {
            comestibleComprado1.setComestible(comestible1);
            comestibleComprado1.setCompra(compraInicial);

            comestibleComprado2.setComestible(comestible2);
            comestibleComprado2.setCompra(compraInicial);

            confiteriaComprada.add(comestibleComprado1);
            confiteriaComprada.add(comestibleComprado2);
        }

        try {
            Compra compra = clienteServicio.comprarConfiteria(compraInicial, confiteriaComprada);
            Assertions.assertEquals(confiteriaComprada, compra.getComprasConfiteria());
            System.out.println(compra.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void elegirMetodoPago() {
        Compra compraInicial = compraRepo.findById(2).orElse(null);

        try {
            Compra compra = clienteServicio.elegirMetodoPago(compraInicial, MetodoPago.EFECTY);
            Assertions.assertEquals(MetodoPago.EFECTY, compra.getMetodoPago());
            System.out.println(compra.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void finalizarCompra() {
        Compra compraInicial = compraRepo.findById(5).orElse(null);

        try {
            Compra compra = clienteServicio.registrarCompra(compraInicial);
            assert compraInicial != null;
            Assertions.assertEquals( compraInicial.getCliente(), compra.getCliente() );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void agregarCupon() {

        try {
            Cliente cliente = clienteServicio.obtener("3344");
            CuponCliente cuponCliente = clienteServicio.agregarCupon("Halo", cliente);

            Assertions.assertTrue(cliente.getCuponesCliente().contains(cuponCliente));
            cliente.getCuponesCliente().forEach(System.out::println);
            System.out.println( cuponCliente.getCliente().getNombreCompleto() );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesCiudad() throws Exception {
        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        try {
            if(ciudad != null){
                List<Funcion> funciones = clienteServicio.filtrarFuncionesCiudad(ciudad.getId());
                Assertions.assertEquals(3, funciones.size());
                funciones.forEach(System.out::println);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudades() {
        List<Ciudad> ciudades = clienteServicio.obtenerCiudades();
        Assertions.assertNotNull(ciudades);

        ciudades.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudad() {
        try {
            Ciudad ciudadEncontrada = clienteServicio.obtenerCiudad(2);
            Assertions.assertEquals("Pereira", ciudadEncontrada.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void enviarEmailTest() {
        emailServicio.enviarEmail("Primera prueba", "Esto es un mensaje de prueba", "samuel.echeverrib@uqvirtual.edu.co");
    }

    @Test
    public void cumpleaniosTest() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate cumpleanios = LocalDate.parse("2003-10-24");

        boolean flag = fechaActual.getMonth() == cumpleanios.getMonth() &&
                       fechaActual.getDayOfMonth() == cumpleanios.getDayOfMonth();

        Assertions.assertTrue(flag);
    }
}
