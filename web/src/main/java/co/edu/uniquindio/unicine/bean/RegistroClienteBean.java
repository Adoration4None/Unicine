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
public class RegistroClienteBean implements Serializable {
    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private String nombresCliente;

    @Getter @Setter
    private String apellidosCliente;

    @Getter @Setter
    private String confirmacionContrasena;

    // Servicio de negocio
    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private boolean registroExitoso;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
        registroExitoso = false;
    }

    public void registrarCliente() {
        cliente.setNombreCompleto(nombresCliente + " " + apellidosCliente);

        if( cliente.getContrasena().equals(confirmacionContrasena) ) {
            try {
                clienteServicio.registrar(cliente);
                registroExitoso = true;

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", "Activa tu cuenta ingresando al enlace enviado a tu correo electronico");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro", fm);
            }
            catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_registro", fm);
            }
        }
        else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden");
            FacesContext.getCurrentInstance().addMessage("mensaje_registro", fm);
        }

    }

}
