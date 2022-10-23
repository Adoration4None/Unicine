//package co.edu.uniquindio.unicine.test.servicios;
//
//import co.edu.uniquindio.unicine.entidades.*;
//import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
//import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
//import co.edu.uniquindio.unicine.servicios.ClienteServicio;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@Transactional
//public class AdminTeatroServicioTest {
//
//    @Autowired
//    private AdminTeatroServicio adminTeatroServicio;
//
//    @Autowired
//    private ClienteServicio clienteServicio;
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public AdministradorTeatro iniciarSesion(String email, String contrasena) throws Exception {
//        try{
//            AdministradorTeatro adminTeatroLogeado = adminTeatroServicio.iniciarSesion("virginiaingram@outlook.couk", "QWL22XGI4SX");
//            Assertions.assertEquals("Virginia Ingram", adminTeatroLogeado.getNombreCompleto());
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    // Gestionar teatros --------------------------------------------------------------------------------------
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Teatro crearTeatro(Teatro teatro) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Teatro actualizarTeatro(Teatro teatro) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public void eliminarTeatro(Integer idTeatro) throws Exception {
//        try{
//            adminTeatroServicio.eliminarTeatro(1);
//
//            Teatro teatroPrueba = adminTeatroServicio.obtenerTeatro(1);
//            Assertions.assertNull(teatroPrueba);
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Teatro obtenerTeatro(Integer idTeatro) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public List<Teatro> listarTeatros() {
//        List<Teatro> teatros = adminTeatroServicio.listarTeatros();
//
////        Assertions.assertEquals();
//        List<Cliente> clientes = clienteServicio.listarClientes();
//
//        Assertions.assertEquals(5, clientes.size() );
//        clientes.forEach(System.out::println);
//    }
//
//
//    // Gestionar salas ----------------------------------------------------------------------------------------
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Sala crearSala(Sala sala) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Sala actualizarSala(Sala sala) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public void eliminarSala(Integer idSala) throws Exception {
//        try{
//            adminTeatroServicio.eliminarSala(3);
//
//            Sala salaPrueba = adminTeatroServicio.obtenerSala(3);
//            Assertions.assertNull(salaPrueba);
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Sala obtenerSala(Integer idSala) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public List<Sala> listarSalas() {
//
//    }
//
//    // Gestionar funciones ------------------------------------------------------------------------------------
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Funcion crearFuncion(Funcion funcion) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public void eliminarFuncion(Integer idFuncion) throws Exception {
//        adminTeatroServicio.eliminarFuncion(2);
//
//        Funcion funcionPrueba = adminTeatroServicio.obtenerFuncion(2);
//        Assertions.assertNull(funcionPrueba);
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public Funcion obtenerFuncion(Integer idFuncion) throws Exception {
//
//    }
//
//    @Test
//    @Sql("classpath:dataset.sql")
//    public List<Funcion> listarFunciones() {
//
//    }
//}
