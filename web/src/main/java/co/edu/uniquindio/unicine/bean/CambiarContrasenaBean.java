package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class CambiarContrasenaBean implements Serializable {
    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['p1']}")
    private String param1;

    @Value("#{param['p2']}")
    private String param2;

    @Getter @Setter
    private String contrasena;

    @Getter @Setter
    private String confirmacionContrasena;

    public String cambiarContrasena() {
        try {
            if( contrasena.equals(confirmacionContrasena) ) {
                clienteServicio.cambiarContrasena(param1, param2, contrasena);
                return "/index?faces-redirect=true";
            }
            else mostrarError(new Exception("Las contrasenas ingresadas no coinciden") );
        } catch (Exception e) {
            mostrarError(e);
        }

        return "";
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        PrimeFaces.current().dialog().showMessageDynamic(fm);
    }
}
