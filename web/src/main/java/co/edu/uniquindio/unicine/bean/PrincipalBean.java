package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class PrincipalBean implements Serializable {
    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private String nombreCiudad;

    @Getter @Setter
    private String busquedaPelicula;

    @Getter @Setter
    private List<Pelicula> peliculasEstrenoCiudad, peliculasPreventaCiudad, peliculasBusqueda, peliculasTeatro;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Ciudad ciudadActual;

    @Getter
    @Value("#{param['city']}")
    private String idCiudadParam;

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Teatro> teatrosCiudad;

    @Getter @Setter
    private boolean teatroSeleccionado;

    @Getter @Setter
    private boolean ciudadSeleccionada;

    @Getter @Setter
    private List<String> imagenesPortada = new ArrayList<>();

    @PostConstruct
    public void init() {
        ciudades = clienteServicio.obtenerCiudades();

        imagenesPortada.add("https://images.unsplash.com/photo-1617914309185-9e63b3badfca?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80");
        imagenesPortada.add("https://pbs.twimg.com/media/FOiDBN7WQAEeIzx.jpg:large");
        imagenesPortada.add("https://cinematicslant.files.wordpress.com/2017/09/blade-runner-2049-banner.jpg");

        if(idCiudadParam != null && !idCiudadParam.isEmpty()) {
            ciudadSeleccionada = true;

            try {
                nombreCiudad = clienteServicio.obtenerCiudad( Integer.valueOf(idCiudadParam) ).getNombre();
                peliculasEstrenoCiudad = clienteServicio.filtrarPeliculasEstadoCiudad( Integer.valueOf(idCiudadParam), EstadoPelicula.ESTRENO );
                peliculasPreventaCiudad = clienteServicio.filtrarPeliculasEstadoCiudad( Integer.valueOf(idCiudadParam), EstadoPelicula.PREVENTA );

                teatrosCiudad = adminTeatroServicio.obtenerTeatrosCiudad( Integer.valueOf(idCiudadParam) );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void seleccionarTeatro() {
        if(teatro != null) {
            teatroSeleccionado = true;

        }
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
    }
}
