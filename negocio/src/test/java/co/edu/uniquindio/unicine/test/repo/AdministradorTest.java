package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorTeatroRepo administradorTeatroRepo;

    // CRUD ---------------------------------------------------------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crear() {
        AdministradorTeatro administrador = new AdministradorTeatro("009988", "Javier Salinas", "ja.s@mail,com", "admin123");

        administrador.agregarTelefono("3445566", "Celular");
        administrador.agregarTelefono("79998", "Fijo");

        AdministradorTeatro guardado = administradorTeatroRepo.save(administrador);

        Assertions.assertNotNull(guardado);
        Assertions.assertEquals( administrador.getNombreCompleto(), guardado.getNombreCompleto() );
    }
}
