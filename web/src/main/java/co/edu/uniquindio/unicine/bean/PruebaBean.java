package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class PruebaBean {
    @Autowired
    private ClienteServicio clienteServicio;

    @Getter
    @Setter
    private String atributo1, atributo2;

    //metodo para cambiar ambos atributos
    public void cambiar(){
        String temp = atributo1;
        atributo1 = atributo2;
        atributo2 = temp;
    }
}
