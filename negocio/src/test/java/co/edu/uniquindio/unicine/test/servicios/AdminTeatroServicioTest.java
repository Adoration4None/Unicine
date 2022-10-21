package co.edu.uniquindio.unicine.test.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AdminTeatroServicioTest {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;


    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion(String email, String contrasena) throws Exception {
        try{
            AdministradorTeatro adminTeatroLogeado = adminTeatroServicio.iniciarSesion("virginiaingram@outlook.couk", "QWL22XGI4SX");
            Assertions.assertEquals("Virginia Ingram", adminTeatroLogeado.getNombreCompleto());
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    // Gestionar teatros --------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearTeatro(Teatro teatro) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatro(Teatro teatro) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro(Integer idTeatro) throws Exception {
        try{
            adminTeatroServicio.eliminarTeatro(1);

            Teatro teatroPrueba = adminTeatroServicio.obtenerTeatro(1);
            Assertions.assertNull(teatroPrueba);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatro(Integer idTeatro) throws Exception {
        try {
            Teatro teatroEncontrado = adminTeatroServicio.obtenerTeatro(4);
            Assertions.assertEquals("Arboleda Theaters", teatroEncontrado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatros() {
        List<Teatro> teatros = adminTeatroServicio.listarTeatros();

        Assertions.assertEquals(5, teatros.size());
        teatros.forEach(System.out::println);
    }


    // Gestionar salas ----------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearSala(Sala sala) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSala(Sala sala) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSala(Integer idSala) throws Exception {
        try{
            adminTeatroServicio.eliminarSala(3);

            Sala salaPrueba = adminTeatroServicio.obtenerSala(3);
            Assertions.assertNull(salaPrueba);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerSala(Integer idSala) throws Exception {
        try {
            Sala salaEncontrada = adminTeatroServicio.obtenerSala(3);
            Assertions.assertEquals("SALA_XD", salaEncontrada.getTipo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalas() {
        List<Sala> salas = adminTeatroServicio.listarSalas();

        Assertions.assertEquals(5, salas.size());
        salas.forEach(System.out::println);
    }

    // Gestionar funciones ------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearFuncion(Funcion funcion) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarFuncion(Funcion funcion) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncion(Integer idFuncion) throws Exception {
        adminTeatroServicio.eliminarFuncion(2);

        Funcion funcionPrueba = adminTeatroServicio.obtenerFuncion(2);
        Assertions.assertNull(funcionPrueba);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncion(Integer idFuncion) throws Exception {
        try {
            Funcion funcionEncontrada = adminTeatroServicio.obtenerFuncion(4);
            Assertions.assertEquals("The Truman Show", funcionEncontrada.getPelicula().getNombre()); //no estoy seguro de que funcione bien
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFunciones() {
        List<Funcion> funciones = adminTeatroServicio.listarFunciones();

        Assertions.assertEquals(5, funciones.size());
        funciones.forEach(System.out::println);
    }
}
