package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
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
    private List<Funcion> funcionesEstrenoCiudad, funcionesPreventaCiudad, funcionesBusqueda;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Ciudad ciudadActual;

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Teatro> teatrosCiudad;

    @Getter @Setter
    private boolean ciudadSeleccionada;

    @PostConstruct
    public void init() {
        ciudades = clienteServicio.obtenerCiudades();
    }

    public void seleccionarCiudad(){
        try {
            nombreCiudad = clienteServicio.obtenerCiudad( ciudadActual.getId() ).getNombre();
            funcionesEstrenoCiudad = clienteServicio.filtrarFuncionesEstadoCiudad( ciudadActual.getId(), EstadoPelicula.ESTRENO );
            funcionesPreventaCiudad = clienteServicio.filtrarFuncionesEstadoCiudad( ciudadActual.getId(), EstadoPelicula.PREVENTA );
            ciudadSeleccionada = true;

            teatrosCiudad = adminTeatroServicio.obtenerTeatrosCiudad( ciudadActual.getId() );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void buscarPelicula() {
        if(ciudadActual != null) {
            try {
                funcionesBusqueda = clienteServicio.buscarFunciones(busquedaPelicula, ciudadActual.getId() );
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
