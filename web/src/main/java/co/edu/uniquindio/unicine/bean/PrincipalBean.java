package co.edu.uniquindio.unicine.bean;

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

    @Value("#{param['city-id']}")
    private String ciudadParam;

    @Getter @Setter
    private String nombreCiudad;

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    private List<Funcion> funcionesCiudad;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void init() {
        if(ciudadParam != null && !ciudadParam.equals("")) {
            try {
                funcionesCiudad = clienteServicio.filtrarFuncionesCiudad(Integer.valueOf(ciudadParam));
                nombreCiudad = clienteServicio.obtenerCiudad(Integer.valueOf(ciudadParam)).getNombre();
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    public void buscarPelicula() {
        if(!busqueda.isEmpty()) {
            try {
                funcionesCiudad = clienteServicio.buscarFunciones(busqueda, Integer.valueOf(ciudadParam));
                reload();
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    public void reload() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            mostrarError(e);
        }
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
    }
}
