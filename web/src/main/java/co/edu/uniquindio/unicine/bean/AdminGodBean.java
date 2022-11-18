package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
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
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AdminGodBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter
    @Setter
    private AdministradorTeatro administrador;

    @Getter
    @Setter
    private List<AdministradorTeatro> administradores;

    @Getter
    @Setter
    private List<AdministradorTeatro> administradoresSeleccionados;

    private Boolean editar;

    public AdminGodBean(AdministradorServicio administradorServicio) {
        this.administradorServicio = administradorServicio;
    }

    @PostConstruct
    public void init() {
        editar = false;
        administrador = new AdministradorTeatro();
        administradores = administradorServicio.listarAdministradores();
        administradoresSeleccionados = new ArrayList<>();
    }

    public void crearAdmin() {
        try {
            if (!editar) {
                AdministradorTeatro admin = administradorServicio.crearAdministrador(administrador);
                administradores.add(admin);
                administrador = new AdministradorTeatro();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de administrador exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            } else {
                administradorServicio.actualizarAdministrador(administrador);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Administrador actualizado");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public void eliminarAdmins() {
        try {
            for (AdministradorTeatro admin : administradoresSeleccionados) {
                administradorServicio.eliminarAdministrador(admin.getCedula());
                administradores.remove(admin);
            }
            administradoresSeleccionados.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Administrador eliminado");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public String getTextoBtnBorrar() {
        if (administradoresSeleccionados.size() == 0) {
            return "Borrar";
        } else {
            return administradoresSeleccionados.size() == 1 ? "Borrar 1 elemento" : "Borrar " + administradoresSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar administrador" : "Crear administrador";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.administrador = new AdministradorTeatro();
        editar = false;
    }

    public void seleccionarAdmin(AdministradorTeatro admin) {
        this.administrador = admin;
        editar = true;
    }
}
