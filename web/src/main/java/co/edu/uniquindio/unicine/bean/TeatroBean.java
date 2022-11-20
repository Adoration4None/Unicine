package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeatroBean implements Serializable {

    @Autowired
    private AdminTeatroServicio administradorTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter
    @Setter
    private Teatro teatro;

    @Getter
    @Setter
    private List<Teatro> teatros;

    @Getter
    @Setter
    private List<Teatro> teatrosSeleccionados;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    private Boolean editar;

    private List<AdministradorTeatro> adminPrueba;

    public TeatroBean(AdminTeatroServicio administradorTeatroServicio) {
        this.administradorTeatroServicio = administradorTeatroServicio;
    }

    @PostConstruct
    public void init() {
        adminPrueba = administradorServicio.listarAdministradores();
       // adminPrueba = new AdministradorTeatro("1234", "juaan", "juaaaaan@gmail.com", "123");
        editar = false;
        teatro = new Teatro();
        teatros = administradorTeatroServicio.listarTeatros();
        ciudades = administradorServicio.listarCiudades();
        teatrosSeleccionados = new ArrayList<>();
    }

    public void crearTeatro() {
        try {
            if (!editar) {
                teatro.setAdministrador(adminPrueba.get(0));
                Teatro t = administradorTeatroServicio.crearTeatro(teatro);
                teatros.add(t);
                teatro = new Teatro();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de teatro exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            } else {
                administradorTeatroServicio.actualizarTeatro(teatro);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro actualizado");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public void eliminarTeatros() {
        try {
            for (Teatro teatro: teatrosSeleccionados) {
                administradorTeatroServicio.eliminarTeatro(teatro.getId());
                teatros.remove(teatro);
            }
            teatrosSeleccionados.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro eliminado");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public String getTextoBtnBorrar() {
        if (teatrosSeleccionados.size() == 0) {
            return "Borrar";
        } else {
            return teatrosSeleccionados.size() == 1 ? "Borrar 1 elemento" : "Borrar " + teatrosSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar teatro" : "Crear teatro";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.teatro = new Teatro();
        editar = false;
    }

    public void seleccionarTeatro(Teatro teatro) {
        this.teatro = teatro;
        editar = true;
    }
}
