package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeatroTest {

    @Autowired
    private TeatroRepo teatroRepo;

    // CRUD -----------------------------------------------------------------------------------------------------
    public void crear() {

    }

    public void eliminar() {

    }

    public void actualizar() {

    }

    public void obtener() {

    }

    // Otras consultas ------------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatrosCiudad() {
        List<Teatro> teatrosArmenia = teatroRepo.obtenerTeatrosCiudad("Armenia");

        teatrosArmenia.forEach(System.out::println);
        System.out.println(teatrosArmenia.size());
    }
}
