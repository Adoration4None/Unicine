package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
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
        tipoSesion = -1;
    }

    public String iniciarSesionCliente() {
        try {
            personaIngresada = clienteServicio.iniciarSesion(email, contrasena);

            if(personaIngresada != null) {
                tipoSesion = 2;
                autenticado = true;
                cliente = (Cliente) personaIngresada;

                if(ciudadSeleccionada != null)
                    return "/index?faces-redirect=true&amp;city=" + ciudadSeleccionada.getId();
                else
                    return "/index?faces-redirect=true";
            }

        } catch (Exception e) {
            mostrarError(e);
        }

        return "";
    }

    public String iniciarSesionAdmins() {
        try {
            personaIngresada = administradorServicio.iniciarSesion(email, contrasena);

            if(personaIngresada != null) {
                tipoSesion = 0;
                autenticado = true;

                return "/admin/index_admin?faces-redirect=true";
            }
            else {
                personaIngresada = adminTeatroServicio.iniciarSesion(email, contrasena);

                if(personaIngresada != null) {
                    tipoSesion = 1;
                    autenticado = true;

                    return "/admin_teatro/index_admin_teatro?faces-redirect=true";
                }
            }

        } catch (Exception e) {
            mostrarError(e);
        }

        return "";
    }

    public void iniciarSesionGoogle() {

    }

    public String cerrarSesion() {
        int tipoSesion = getTipoSesion();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        if(tipoSesion == 2)
            return "/index?faces-redirect=true";

        return "/login_admin?faces-redirect=true";
    }

    public String seleccionarCiudad(Ciudad ciudad){
        if(ciudad != null) {
            ciudadSeleccionada = ciudad;

            return "/index?faces-redirect=true&amp;city=" + ciudad.getId();
        }

        return "";
    }

    public String restablecerCiudad() {
        ciudadSeleccionada = null;
        return "/index?faces-redirect=true";
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_login", fm);
    }

}
