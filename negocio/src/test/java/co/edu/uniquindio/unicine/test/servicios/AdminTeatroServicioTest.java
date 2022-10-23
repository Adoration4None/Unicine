package co.edu.uniquindio.unicine.test.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AdminTeatroServicioTest {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion() throws Exception {
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
    public void crearTeatro() throws Exception {
        Teatro teatroCrear = new Teatro(2, "Hola", "Hi", null, null, null);

        try{
            Teatro nuevo = adminTeatroServicio.crearTeatro(teatroCrear);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatro() throws Exception {
        try {
            Teatro teatroActualizar = adminTeatroServicio.obtenerTeatro(4);
            teatroActualizar.setNombre("Actualizaoooo");

            Teatro teatroActualizado = adminTeatroServicio.actualizarTeatro(teatroActualizar);
            Assertions.assertEquals("Actualizaoooo", teatroActualizado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro() throws Exception {
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
    public void obtenerTeatro() throws Exception {
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
    public void crearSala() throws Exception {
        Sala salaCrear = new Sala(2, TipoSala.SALA_XD, null);

        try{
            Sala nueva = adminTeatroServicio.crearSala(salaCrear);
            Assertions.assertNotNull(nueva);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSala() throws Exception {
        try {
            Sala salaActualizar = adminTeatroServicio.obtenerSala(4);
            salaActualizar.setCantidadSillas(6);

            Sala salaActualizada = adminTeatroServicio.actualizarSala(salaActualizar);
            Assertions.assertEquals(6, salaActualizada.getCantidadSillas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSala() throws Exception {
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
    public void obtenerSala() throws Exception {
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
    public void crearFuncion() throws Exception {
        Funcion funcionCrear = new Funcion(TipoFuncion.FUNCION_XD, 34F, null, null, null);

        try{
            Funcion nueva = adminTeatroServicio.crearFuncion(funcionCrear);
            Assertions.assertNotNull(nueva);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //no entiendo bien como funciona
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarFuncion() throws Exception {
        try {
            Funcion funcionActualizar = adminTeatroServicio.obtenerFuncion(3);
            funcionActualizar.setPrecio(50000F);

            Funcion funcionActualizada = adminTeatroServicio.actualizarFuncion(funcionActualizar);
            Assertions.assertEquals(50000F, funcionActualizada.getPrecio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncion() throws Exception {
        adminTeatroServicio.eliminarFuncion(2);

        Funcion funcionPrueba = adminTeatroServicio.obtenerFuncion(2);
        Assertions.assertNull(funcionPrueba);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncion() throws Exception {
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
