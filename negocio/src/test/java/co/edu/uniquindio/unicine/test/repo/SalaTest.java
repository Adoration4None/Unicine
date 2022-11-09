package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.repo.SalaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalaTest {

    @Autowired
    private SalaRepo salaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesSala() {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verificarAsientosDisponibles() {

    }
}
