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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AdministradorServicioTest {
    private AdministradorServicio administradorServicio;

    //este no tiene ninguna tabla con la cual comparar, entonces no s si hacerle el test
    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion(String email, String contrasena) {

    }

    // Gestionar administradores de teatros ----------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearAdministrador() throws Exception {
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
    public void actualizarAdministrador() throws Exception {
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
    public void eliminarAdministrador() throws Exception {
        administradorServicio.eliminarPelicula("One Piece Film: Red");
        Pelicula peliculaPrueba = administradorServicio.obtenerPelicula("One Piece Film: Red");

        Assertions.assertNull(peliculaPrueba);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministrador() throws Exception {
        try {
            AdministradorTeatro administradorTeatroEncontrado = administradorServicio.obtenerAdministrador("3346");
            Assertions.assertEquals("Lewis Good", administradorTeatroEncontrado.getNombreCompleto()); //no estoy seguro de que funcione bien
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
    public void crearPelicula() throws Exception {
        Pelicula peliculaCrear = new Pelicula("Bocadillo", "ruta imagen 21321", "ruta trailer 23123123", null, null, null);
        try{
            Pelicula nueva = administradorServicio.crearPelicula(peliculaCrear);
            Assertions.assertNotNull(nueva);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPelicula() throws Exception {
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
    public void eliminarPelicula() throws Exception {
        administradorServicio.eliminarPelicula("One Piece Film: Red");
        Pelicula peliculaPrueba = administradorServicio.obtenerPelicula("One Piece Film: Red");

        Assertions.assertNull(peliculaPrueba);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPelicula() throws Exception {
        try {
            Pelicula peliculaEncontrada = administradorServicio.obtenerPelicula("The Truman Show");
            Assertions.assertEquals("ruta imagen 5", peliculaEncontrada.getImagen()); //no estoy seguro de que funcione bien
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
    public void crearComestible() throws Exception {
        Confiteria comestibleCrear = new Confiteria("Bianchi barra", "Ruta imagen 34234", 4, null, null);
        try{
            Confiteria nueva = administradorServicio.crearComestible(comestibleCrear);
            Assertions.assertNotNull(nueva);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarComestible() throws Exception {
        try {
            Confiteria comestibleActualizar = administradorServicio.obtenerComestible(4);
            comestibleActualizar.setNombre("Cerveza");

            Confiteria confiteriaActualizada = administradorServicio.actualizarComestible(comestibleActualizar);
            Assertions.assertEquals("Cerveza", comestibleActualizar.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarComestible() throws Exception {
        administradorServicio.eliminarComestible(4);
        Confiteria comestiblePrueba = administradorServicio.obtenerComestible(4);

        Assertions.assertNull(comestiblePrueba);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComestible() throws Exception {
        try {
            Confiteria comestibleEncontrado = administradorServicio.obtenerComestible(5);
            Assertions.assertEquals("Hamburguesa", comestibleEncontrado.getNombre()); //no estoy seguro de que funcione bien
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarConfiteria() {
        List<Confiteria> comestibles = administradorServicio.listarConfiteria();
        Assertions.assertEquals(5, comestibles.size());
        comestibles.forEach(System.out::println);
    }

    // Gestionar cupones -----------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearCupon() throws Exception {
        Cupon cuponCrear = new Cupon("Cupon magico", 5000F, null, null, null);
        try{
            Cupon nuevo = administradorServicio.crearCupon(cuponCrear);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCupon() throws Exception {
        try {
            Cupon cuponActualizar = administradorServicio.obtenerCupon(4);
            cuponActualizar.setNombre("Miau");

            Cupon cuponActualizado = administradorServicio.actualizarCupon(cuponActualizar);
            Assertions.assertEquals("Miau", cuponActualizado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCupon() throws Exception {
        administradorServicio.eliminarCupon(3);

        Cupon cuponPrueba = administradorServicio.obtenerCupon(3);
        Assertions.assertNull(cuponPrueba);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCupon() throws Exception {
        try {
            Cupon cuponEncontrado = administradorServicio.obtenerCupon(3);
            Assertions.assertEquals("Agosto venteado", cuponEncontrado.getNombre()); //no estoy seguro de que funcione bien
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCupones() {
        List<Cupon> cupones = administradorServicio.listarCupones();
        Assertions.assertEquals(5, cupones.size());
        cupones.forEach(System.out::println);
    }
}
