package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaTest {

    @Autowired
    private PeliculaRepo peliculaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFunciones() {
        List<FuncionDTO> funciones = peliculaRepo.obtenerFunciones("One Piece Film: Red");

        Assertions.assertNotNull(funciones);
        funciones.forEach(System.out::println);
        Assertions.assertEquals(1, funciones.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPelicula() {
        List<Pelicula> peliculas = peliculaRepo.buscarPeliculas("A");

        Assertions.assertNotNull(peliculas);
        peliculas.forEach(System.out::println);

        Assertions.assertEquals(4, peliculas.size());
    }

}
