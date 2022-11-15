package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class PeliculasTeatroBean implements Serializable {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    private AdministradorServicio administradorServicio;

    @Value("#{param['th']}")
    private String idTeatro;

    @Value("#{param['city']}")
    private String idCiudad;

    @Getter @Setter
    private Teatro teatro;

    private List<Pelicula> peliculasTeatro;

    @PostConstruct
    public void init() {
        if(idTeatro != null && !idTeatro.isEmpty()) {
            try {
                teatro = adminTeatroServicio.obtenerTeatro( Integer.valueOf(idTeatro) );

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
