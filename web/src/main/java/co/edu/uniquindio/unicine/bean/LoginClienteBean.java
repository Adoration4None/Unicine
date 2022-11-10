package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
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

@Component
@ViewScoped
public class LoginClienteBean implements Serializable {

    @Getter @Setter
    private String emailCliente, contrasenaCliente;

    @Getter @Setter
    private Cliente clienteIngresado;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void init() {

    }

    public void iniciarSesion() {
        try {
            clienteIngresado = clienteServicio.iniciarSesion(emailCliente, contrasenaCliente);

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesion exitoso", "Has ingresado correctamente!");
            FacesContext.getCurrentInstance().addMessage("mensaje_login", fm);

            System.out.println(clienteIngresado);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_login", fm);
        }
    }

    public void iniciarSesionGoogle() {

    }

}
