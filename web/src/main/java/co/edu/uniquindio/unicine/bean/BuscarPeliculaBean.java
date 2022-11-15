package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Pelicula;
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
import java.util.List;

@Component
@ViewScoped
public class BuscarPeliculaBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['city']}")
    private String idCiudad;

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    @Value("#{param['q']}")
    private String busquedaParam;

    @Getter @Setter
    private String nombreCiudad;

    @Getter @Setter
    private List<Pelicula> peliculasBusqueda;

    @PostConstruct
    public void init() {

        if(idCiudad != null && !idCiudad.isEmpty()) {
            try {
                nombreCiudad = clienteServicio.obtenerCiudad( Integer.valueOf(idCiudad) ).getNombre();

                if(busquedaParam != null && !busquedaParam.isEmpty()) {
                    peliculasBusqueda = clienteServicio.buscarPeliculas(busquedaParam, Integer.valueOf(idCiudad));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public String buscarPelicula() {
        if(busqueda != null && !busqueda.isEmpty())
            return "/resultados_busqueda?faces-redirect=true&amp;city=" + idCiudad + "&amp;q=" + busqueda;
        else
            mostrarError( new Exception("Busqueda vacia. Por favor ingrese una busqueda valida") );

        return "";
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
    }
}
