package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class BuscarCiudadBean implements Serializable {
    @Getter @Setter
    private String busqueda;

    public String buscar() {
        if(busqueda.isEmpty())
            return "";

        return "/principal?faces-redirect=true&amp;city-id=" + busqueda;
    }
}
