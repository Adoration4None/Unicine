package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Funcion;
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
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class BuscarPeliculaBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Value(value = "#{seguridadBean.ciudad.id}")
    private String idCiudad;

    @Getter @Setter
    private String busqueda;

    @Value("#{param['q']}")
    @Getter @Setter
    private String busquedaParam;

    @Getter @Setter
    private String nombreCiudad;

    @Getter @Setter
    private List<Pelicula> peliculasBusqueda;


    @PostConstruct
    public void init() {
        try {
            if(busquedaParam != null && !busquedaParam.isEmpty()) {
                busqueda = busquedaParam;
                peliculasBusqueda = clienteServicio.buscarPeliculas(busquedaParam, Integer.valueOf(idCiudad));
            }
        } catch (Exception e) {
            mostrarError(e);
        }

    }

    public String buscarPelicula() {
        if(busqueda != null && !busqueda.isEmpty())
            //solo se puede utilizar un ? por url, entonces se utiliza un & que va acompañado de amp; para que Java lo codifique bien, q es solo una "variable" con la busqueda
            return "/resultados_busqueda?faces-redirect=true&amp;city=" + idCiudad + "&amp;q=" + busqueda;
        else
            mostrarError( new Exception("Busqueda vacia. Por favor ingrese una busqueda valida") );

        return "";
    }

    public List<Funcion> getFuncionesPelicula(Integer idPelicula) {
        try {
            return clienteServicio.obtenerFuncionesPeliculaCiudad(idPelicula, Integer.valueOf(idCiudad) );
        } catch (Exception e) {
            mostrarError(e);
        }

        return null;
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
    }
}
