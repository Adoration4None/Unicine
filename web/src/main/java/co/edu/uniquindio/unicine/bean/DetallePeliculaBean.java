package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
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
public class DetallePeliculaBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['mov']}")
    private String nombrePelicula;

    @Value("#{param['city']}")
    private String idCiudad;

    @Getter @Setter
    private String nombreCiudad;

    @Getter @Setter
    private Pelicula pelicula;

    @Getter @Setter
    private List<Funcion> funcionesPelicula;

    @PostConstruct
    public void init() {

        if(nombrePelicula != null && !nombrePelicula.isEmpty()) {
            try {
                pelicula = administradorServicio.obtenerPelicula(nombrePelicula);
                funcionesPelicula = clienteServicio.obtenerFuncionesPelicula(nombrePelicula);
                nombreCiudad = clienteServicio.obtenerCiudad( Integer.valueOf(idCiudad) ).getNombre();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
