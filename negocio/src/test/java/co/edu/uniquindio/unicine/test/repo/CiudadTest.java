package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.entidades.Ciudad;
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

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatros() {

        List<Teatro> teatrosArmenia = ciudadRepo.obtenerTeatros("Armenia");
        List<Teatro> teatrosPereira = ciudadRepo.obtenerTeatros("Pereira");
        List<Teatro> teatrosManizales = ciudadRepo.obtenerTeatros("Manizales");

        Assertions.assertNotNull(teatrosArmenia); //tiene que dar 2
        Assertions.assertNotNull(teatrosPereira); //tiene que dar 1
        Assertions.assertNotNull(teatrosManizales); //tiene que dar 1

        teatrosPereira.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudades() {
        List<Ciudad> ciudades = ciudadRepo.obtenerCiudades();

        Assertions.assertNotNull(ciudades);
        ciudades.forEach(System.out::println);
    }
}
