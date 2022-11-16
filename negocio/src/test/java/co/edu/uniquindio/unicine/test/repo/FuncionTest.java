package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
import co.edu.uniquindio.unicine.repo.HorarioRepo;
import co.edu.uniquindio.unicine.repo.SalaRepo;
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

    @Autowired
    private HorarioRepo horarioRepo;

    @Autowired
    private SalaRepo salaRepo;

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
        List<Funcion> funciones = funcionRepo.buscarPeliculas("A", 1);

        Assertions.assertNotNull(funciones);
        funciones.forEach(System.out::println);

        Assertions.assertEquals(4, funciones.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesSalaHorario() {
        Horario horarioPrueba = horarioRepo.findById(1).orElse(null);
        Sala    salaPrueba    = salaRepo.findById(2).orElse(null);

        Funcion funcion = funcionRepo.findById(1).orElse(null);
        if(funcion != null) {
            funcion.setSala(salaPrueba);
            funcion.setHorario(horarioPrueba);
            funcionRepo.save(funcion);
        }

        Funcion funcion2 = funcionRepo.findById(2).orElse(null);
        if(funcion2 != null) {
            funcion2.setSala(salaPrueba);
            funcion2.setHorario(horarioPrueba);
            funcionRepo.save(funcion2);
        }

        assert salaPrueba != null;
        assert horarioPrueba != null;
        List<Funcion> funcionesEncontradas = funcionRepo.obtenerFuncionesSalaHorario(salaPrueba.getId(), horarioPrueba.getId());

        Assertions.assertEquals(2, funcionesEncontradas.size() );
        funcionesEncontradas.forEach(System.out::println);
    }
}
