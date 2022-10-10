package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.repo.HorarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    @Autowired
    private HorarioRepo horarioRepo;

    // CRUD ------------------------------------------------------------------------------------------------------

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
}
