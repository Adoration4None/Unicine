package co.edu.uniquindio.unicine.test.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AdministradorServicioTest {
    @Autowired
    private AdministradorServicio administradorServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion() throws Exception {
        boolean comprobacion = administradorServicio.iniciarSesion("administradorunicine@gmail.com", "samuelyfelipe");
        Assertions.assertTrue(comprobacion);
    }

    // Gestionar administradores de teatros ----------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearAdministrador() {
        AdministradorTeatro adminTeatroCrear = new AdministradorTeatro("11267865543", "Henry Hernando", null, null);

        try{
            AdministradorTeatro nuevo = administradorServicio.crearAdministrador(adminTeatroCrear);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdministrador() {
        try {
            AdministradorTeatro adminTeatroActualizar = administradorServicio.obtenerAdministrador("2454");
            adminTeatroActualizar.setNombreCompleto("Juan Felipe Lopez");

            AdministradorTeatro adminTeatroActualizado = administradorServicio.actualizarAdministrador(adminTeatroActualizar);
            Assertions.assertEquals("Juan Felipe Lopez", adminTeatroActualizado.getNombreCompleto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdministrador() {

        try {
            administradorServicio.eliminarAdministrador("2454");
            AdministradorTeatro administradorPrueba = administradorServicio.obtenerAdministrador("2454");

            Assertions.assertNull(administradorPrueba);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministrador() {
        try {
            AdministradorTeatro administradorTeatroEncontrado = administradorServicio.obtenerAdministrador("3346");
            Assertions.assertEquals("Lewis Good", administradorTeatroEncontrado.getNombreCompleto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradores() {
        List<AdministradorTeatro> administradoresTeatro = administradorServicio.listarAdministradores();
        Assertions.assertEquals(5, administradoresTeatro.size());
        administradoresTeatro.forEach(System.out::println);
    }

    // Gestionar peliculas ---------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearPelicula() {
        Pelicula peliculaCrear = new Pelicula("Bocadillo", "ruta imagen 21321", "ruta trailer 23123123", "", "", EstadoPelicula.ESTRENO);
        try{
            Pelicula nueva = administradorServicio.crearPelicula(peliculaCrear);
            Assertions.assertNotNull(nueva);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPelicula() {
        try {
            Pelicula peliculaActualizar = administradorServicio.obtenerPelicula("The Batman");
            peliculaActualizar.setImagen("ruta imagen 56");

            Pelicula peliculaActualizada = administradorServicio.actualizarPelicula(peliculaActualizar);
            Assertions.assertEquals("ruta imagen 56", peliculaActualizada.getImagen());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPelicula() {

        try {
            administradorServicio.eliminarPelicula("One Piece Film: Red");
            Pelicula peliculaPrueba = administradorServicio.obtenerPelicula("One Piece Film: Red");
            Assertions.assertNull(peliculaPrueba);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPelicula() {
        try {
            Pelicula peliculaEncontrada = administradorServicio.obtenerPelicula("The Truman Show");
            Assertions.assertEquals("ruta imagen", peliculaEncontrada.getImagen());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculas() {
        List<Pelicula> peliculas = administradorServicio.listarPeliculas();
        Assertions.assertEquals(5, peliculas.size());
        peliculas.forEach(System.out::println);
    }

    // Gestionar confiteria --------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearComestible()  {
        Confiteria comestibleCrear = new Confiteria("Bianchi barra", "Ruta imagen 34234", 4, 0f, "");

        try{
            Confiteria nueva = administradorServicio.crearComestible(comestibleCrear);
            Assertions.assertNotNull(nueva);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarComestible() {
        try {
            Confiteria comestibleActualizar = administradorServicio.obtenerComestible(4);
            comestibleActualizar.setNombre("Cerveza");

            Confiteria confiteriaActualizada = administradorServicio.actualizarComestible(comestibleActualizar);
            Assertions.assertEquals("Cerveza", comestibleActualizar.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarComestible() {
        try {
            administradorServicio.eliminarComestible(4);
            Confiteria comestiblePrueba = administradorServicio.obtenerComestible(4);

            Assertions.assertNull(comestiblePrueba);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComestible() {
        try {
            Confiteria comestibleEncontrado = administradorServicio.obtenerComestible(5);
            Assertions.assertEquals("Hamburguesa", comestibleEncontrado.getNombre()); //no estoy seguro de que funcione bien
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void listarConfiteria() {
        List<Confiteria> comestibles = administradorServicio.listarConfiteria();
        Assertions.assertEquals(5, comestibles.size());
    }

    // Gestionar cupones -----------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearCupon() {
        Cupon cuponCrear = new Cupon("Cupon magico", 5000F, LocalDate.now(), "", CriterioCupon.HALLOWEEN);
        try{
            Cupon nuevo = administradorServicio.crearCupon(cuponCrear);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCupon() {
        try {
            Cupon cuponActualizar = administradorServicio.obtenerCupon(4);
            cuponActualizar.setNombre("Miau");

            Cupon cuponActualizado = administradorServicio.actualizarCupon(cuponActualizar);
            Assertions.assertEquals("Miau", cuponActualizado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCupon() {
        try {
            administradorServicio.eliminarCupon(3);

            Cupon cuponPrueba = administradorServicio.obtenerCupon(3);
            Assertions.assertNull(cuponPrueba);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCupon() {
        try {
            Cupon cuponEncontrado = administradorServicio.obtenerCupon(3);
            Assertions.assertEquals("Agosto venteado", cuponEncontrado.getNombre()); //no estoy seguro de que funcione bien
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //CLEAN
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCupones() {
        List<Cupon> cupones = administradorServicio.listarCupones();
        Assertions.assertEquals(5, cupones.size());
    }
}