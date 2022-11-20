package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class AgregarCuponBean implements Serializable {
    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['p1']}")
    private String param1;

    @Value("#{param['p2']}")
    private String param2;

    @Getter @Setter
    private String mensaje = "Agregando tu nuevo cupon...";

    @PostConstruct
    public void init() {
        if(param1 != null && !param1.isEmpty() && param2 != null && !param2.isEmpty()) {
            try {
                clienteServicio.agregarCuponCorreo(param1, param2);
                mensaje = "Cupon agregado a tu cuenta";
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    public String redirigir() {
        return "/index?faces-redirect=true";
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        PrimeFaces.current().dialog().showMessageDynamic(fm);
    }
}
