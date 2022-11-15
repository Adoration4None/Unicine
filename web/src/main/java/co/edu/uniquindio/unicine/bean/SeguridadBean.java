package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Persona;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {
    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, contrasena;

    @Getter @Setter
    private Persona personaIngresada;

    @Getter @Setter
    private Ciudad ciudadSeleccionada;

    @Getter @Setter
    private Cliente cliente;

    // 0: Admin, 1: AdminTeatro, 2: Cliente
    @Getter @Setter
    private int tipoSesion;

    @PostConstruct
    public void init() {
        autenticado = false;
    }

    public String iniciarSesionCliente() {
        try {
            personaIngresada = clienteServicio.iniciarSesion(email, contrasena);

            if(personaIngresada != null) {
                tipoSesion = 2;
                autenticado = true;
                cliente = (Cliente) personaIngresada;
                return "/index?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String iniciarSesionAdminTeatro() {
        try {
            personaIngresada = adminTeatroServicio.iniciarSesion(email, contrasena);
            tipoSesion = 1;
            autenticado = true;

            return "/index?faces-redirect=true";
        } catch (Exception e) {
            mostrarError(e);
        }

        return "";
    }

    public String iniciarSesionAdministrador() {
        try {
            personaIngresada = administradorServicio.iniciarSesion(email, contrasena);
            tipoSesion = 0;
            autenticado = true;

            return "/index?faces-redirect=true";
        } catch (Exception e) {
            mostrarError(e);
        }

        return "";
    }

    public void iniciarSesionGoogle() {

    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String seleccionarCiudad(Ciudad ciudad){
        if(ciudad != null) {
            ciudadSeleccionada = ciudad;
            return "/index?faces-redirect=true&amp;city=" + ciudad.getId();
        }


        return "";
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_login", fm);
    }

}
