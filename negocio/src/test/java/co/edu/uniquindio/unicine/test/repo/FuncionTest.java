package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FuncionTest {

    @Autowired
    private FuncionRepo funcionRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaFuncion() {
        String nombrePelicula = funcionRepo.obtenerNombrePelicula(2);

        Assertions.assertNotNull(nombrePelicula);
        Assertions.assertEquals("Taxi Driver", nombrePelicula);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarFunciones() {
        List<Funcion> funciones = funcionRepo.buscarFunciones("A", 1);

        Assertions.assertNotNull(funciones);
        funciones.forEach(System.out::println);

        Assertions.assertEquals(4, funciones.size());
    }
}
