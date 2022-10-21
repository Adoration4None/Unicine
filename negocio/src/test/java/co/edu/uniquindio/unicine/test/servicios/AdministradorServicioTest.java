package co.edu.uniquindio.unicine.test.servicios;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
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

    @Test
    @Sql("classpath:dataset.sql")
    public boolean iniciarSesion(String email, String contrasena) {

    }

    // Gestionar administradores de teatros ----------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public AdministradorTeatro crearAdministrador(AdministradorTeatro administradorTeatro) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public AdministradorTeatro actualizarAdministrador(AdministradorTeatro administradorTeatro) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdministrador(String cedulaAdministrador) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public AdministradorTeatro obtenerAdministrador(String cedulaAdministrador) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public List<AdministradorTeatro> listarAdministradores() {

    }

    // Gestionar peliculas ---------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPelicula(String nombrePelicula) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public Pelicula obtenerPelicula(String nombrePelicula) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public List<Pelicula> listarPeliculas() {

    }

    // Gestionar confiteria --------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public Confiteria crearComestible(Confiteria comestible) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public Confiteria actualizarComestible(Confiteria comestible) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarComestible(Integer idComestible) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public Confiteria obtenerComestible(Integer idComestible) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public List<Confiteria> listarConfiteria() {

    }

    // Gestionar cupones -----------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public Cupon crearCupon(Cupon cupon) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public Cupon actualizarCupon(Cupon cupon) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCupon(Integer idCupon) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public Cupon obtenerCupon(Integer idCupon) throws Exception {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public List<Cupon> listarCupones() {

    }
}
