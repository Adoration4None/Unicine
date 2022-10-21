package co.edu.uniquindio.unicine.test.servicios;

import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class AdministradorServicioTest {
    private AdministradorServicio administradorServicio;

    @Test
    public void iniciarSesion() {

    }
}
