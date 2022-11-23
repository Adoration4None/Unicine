package co.edu.uniquindio.unicine.test.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
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

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private TeatroRepo teatroRepo;

    @Autowired
    private PeliculaRepo peliculaRepo;

    @Autowired
    private SalaRepo salaRepo;

    @Autowired
    private HorarioRepo horarioRepo;

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion() {
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
    public void crearTeatro() {
        Ciudad ciudad = ciudadRepo.findById(2).orElse(null);

        if(ciudad != null) {
            Teatro teatroCrear = new Teatro("Hola", "Hi", ciudad, new AdministradorTeatro());

            try{
                Teatro nuevo = adminTeatroServicio.crearTeatro(teatroCrear);
                Assertions.assertNotNull(nuevo);
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatro() {
        try {
            Teatro teatroActualizar = adminTeatroServicio.obtenerTeatro(4);
            teatroActualizar.setNombre("Actualizaoooo");

            Teatro teatroActualizado = adminTeatroServicio.actualizarTeatro(teatroActualizar);
            Assertions.assertEquals("Actualizaoooo", teatroActualizado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro() {
        try{
            adminTeatroServicio.eliminarTeatro(1);

            Teatro teatroPrueba = adminTeatroServicio.obtenerTeatro(1);
            Assertions.assertNull(teatroPrueba);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatro() {
        try {
            Teatro teatroEncontrado = adminTeatroServicio.obtenerTeatro(5);
            Assertions.assertEquals("Arboleda Theaters", teatroEncontrado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatros() {
        List<Teatro> teatros = adminTeatroServicio.listarTeatros();

        Assertions.assertEquals(5, teatros.size());
        teatros.forEach(System.out::println);
    }

    // Gestionar salas ----------------------------------------------------------------------------------------

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSala() {
        try {
            Sala salaActualizar = adminTeatroServicio.obtenerSala(4);
            salaActualizar.setCantidadSillas(6);

            Sala salaActualizada = adminTeatroServicio.actualizarSala(salaActualizar);
            Assertions.assertEquals(6, salaActualizada.getCantidadSillas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSala() {
        try{
            adminTeatroServicio.eliminarSala(3);

            Sala salaPrueba = adminTeatroServicio.obtenerSala(3);
            Assertions.assertNull(salaPrueba);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerSala() {
        try {
            Sala salaEncontrada = adminTeatroServicio.obtenerSala(3);
            Assertions.assertEquals(65, salaEncontrada.getCantidadSillas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalas() {
        List<Sala> salas = adminTeatroServicio.listarSalas();

        Assertions.assertEquals(5, salas.size());
        salas.forEach(System.out::println);
    }

    // Gestionar funciones ------------------------------------------------------------------------------------

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncion() {
        try {
            adminTeatroServicio.eliminarFuncion(2);

            Funcion funcionPrueba = adminTeatroServicio.obtenerFuncion(2);
            Assertions.assertNull(funcionPrueba);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncion() {
        try {
            Funcion funcionEncontrada = adminTeatroServicio.obtenerFuncion(4);
            Assertions.assertEquals("The Truman Show", funcionEncontrada.getPelicula().getNombre()); //no estoy seguro de que funcione bien
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void listarFunciones() {
        List<Funcion> funciones = adminTeatroServicio.listarFunciones();

        Assertions.assertEquals(5, funciones.size());
        funciones.forEach(System.out::println);
    }
}
