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
import java.io.IOException;
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
    private boolean ciudadSeleccionada;

    @Getter @Setter
    private List<String> imagenesPortada = new ArrayList<>();

    @PostConstruct
    public void init() {
        ciudades = clienteServicio.obtenerCiudades();
        teatro = new Teatro();

        imagenesPortada.add("https://res.cloudinary.com/dheuspgiq/image/upload/v1669094033/unicine/banners/photo-1617914309185-9e63b3badfca_vnbfkg.avif");
        imagenesPortada.add("https://res.cloudinary.com/dheuspgiq/image/upload/v1669094070/unicine/banners/FOiDBN7WQAEeIzx_qpn9jw.jpg");
        imagenesPortada.add("https://res.cloudinary.com/dheuspgiq/image/upload/v1669093600/unicine/banners/5a68f3858cda2516df696f923b88493d_xxffeu.jpg");

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
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("localhost:8080/resultados_busqueda.xhtml?th=" + teatro.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
    }
}
