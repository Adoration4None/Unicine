package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
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
public class AdminTeatroBean implements Serializable {

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

    public AdminTeatroBean(AdministradorServicio administradorServicio) {
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
                administrador.setImagenPerfil("miau");
                AdministradorTeatro admin = administradorServicio.crearAdministrador(administrador);
                administradores.add(admin);
                administrador = new AdministradorTeatro();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de administrador exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            } else {
                administradorServicio.actualizarAdministrador(administrador);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Administrador actualizado");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
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
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
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
