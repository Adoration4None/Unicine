package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.EstadoPersona;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import org.junit.jupiter.api.Assertions;
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

    @Test
    @Sql("classpath:dataset.sql")
    public void validarCupon(){
        boolean flag = cuponRepo.validarCupon(2);
        Assertions.assertTrue(flag);
    }
}
