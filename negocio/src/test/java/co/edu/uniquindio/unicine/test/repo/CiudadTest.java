package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    // CRUD -----------------------------------------------------------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void crear() {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

    }

    // Otras consultas ----------------------------------------------------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatros() {

        List<Teatro> teatrosArmenia = ciudadRepo.obtenerTeatros("Armenia");
        List<Teatro> teatrosPereira = ciudadRepo.obtenerTeatros("Pereira");
        List<Teatro> teatrosManizales = ciudadRepo.obtenerTeatros("Manizales");

        Assertions.assertNotNull(teatrosArmenia);
        Assertions.assertNotNull(teatrosPereira);
        Assertions.assertNotNull(teatrosManizales);

        teatrosArmenia.forEach(System.out::println);
    }
}
