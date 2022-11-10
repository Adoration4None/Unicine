package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class PrincipalBean implements Serializable {

    @Getter @Setter
    private String nombreCiudad;

    @Getter @Setter
    private String busquedaPelicula;

    @Getter @Setter
    private List<Funcion> funcionesCiudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Ciudad ciudadActual;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private boolean ciudadSeleccionada;

    @PostConstruct
    public void init() {
        ciudades = clienteServicio.obtenerCiudades();
    }

    public void seleccionarCiudad(Ciudad ciudad){
        try {
            if(ciudad != null) {
                ciudadActual = ciudad;
                nombreCiudad = clienteServicio.obtenerCiudad( ciudadActual.getId() ).getNombre();
                funcionesCiudad = clienteServicio.filtrarFuncionesCiudad( ciudadActual.getId() );
                ciudadSeleccionada = true;
            }
            else System.out.println("Ciudad nula");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void buscarPelicula() {
        if(ciudadActual != null) {
            try {
                funcionesCiudad = clienteServicio.buscarFunciones(busquedaPelicula, ciudadActual.getId() );
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
    }
}
