package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CiudadBean implements Serializable {
    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter
    @Setter
    private Ciudad ciudad;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Ciudad> ciudadesSeleccionadas;

    private Boolean editar;

    @PostConstruct
    public void init() {
        editar = false;
        ciudad = new Ciudad();
        ciudades = administradorServicio.listarCiudades();
        ciudadesSeleccionadas = new ArrayList<>();
    }

    public void crearCiudad() {
        try {
            if (!editar) {
                Ciudad city = administradorServicio.crearCiudad(ciudad);
                ciudades.add(city);
                ciudad = new Ciudad();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de ciudad exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            } else {
                administradorServicio.actualizarCiudad(ciudad);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Ciudad actualizada");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public void eliminarCiudades() {
        try {
            for (Ciudad city : ciudadesSeleccionadas) {
                administradorServicio.eliminarCiudad(city.getId());
                ciudades.remove(city);
            }
            ciudadesSeleccionadas.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Ciudad eliminada");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "No se puede eliminar la ciudad porque está asociada a otro objeto");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public String getTextoBtnBorrar() {
        if (ciudadesSeleccionadas.size() == 0) {
            return "Borrar";
        } else {
            return ciudadesSeleccionadas.size() == 1 ? "Borrar 1 elemento" : "Borrar " + ciudadesSeleccionadas.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar ciudad" : "Crear ciudad";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.ciudad = new Ciudad();
        editar = false;
    }

    public void seleccionarCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        editar = true;
    }
}
