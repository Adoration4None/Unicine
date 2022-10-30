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

    @PostConstruct
    public void init() {
        cliente = new Cliente();
    }

    public void registrarCliente() {
        cliente.setNombreCompleto(nombresCliente + " " + apellidosCliente);

        if( cliente.getContrasena().equals(confirmacionContrasena) ) {
            try {
                clienteServicio.registrar(cliente);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", "Tu cuenta se ha registrado exitosamente!");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
            catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }
        else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }

    }

}
