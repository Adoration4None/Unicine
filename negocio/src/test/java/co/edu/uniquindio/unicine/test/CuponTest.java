package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.repo.CuponRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuponTest {

    @Autowired
    private CuponRepo cuponRepo;

    // CRUD -----------------------------------------------------------------------------------------------------

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
