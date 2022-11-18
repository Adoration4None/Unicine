package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.EstadoConfiteria;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ComestibleBean implements Serializable {
    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter
    @Setter
    private Confiteria comestible;

    @Getter
    @Setter
    private List<Confiteria> comestibles;

    private List<EstadoConfiteria> estadosConfiteria;

    @Getter
    @Setter
    private List<Confiteria> comestiblesSeleccionados;

    private Boolean editar;

    public ComestibleBean(AdministradorServicio administradorServicio) {
        this.administradorServicio = administradorServicio;
    }

    @PostConstruct
    public void init() {
        editar = false;
        comestible = new Confiteria();
        comestibles = administradorServicio.listarConfiteria();
        comestiblesSeleccionados = new ArrayList<>();
    }

    public void crearComestible() {
        try {
            if (!editar) {
                comestible.setEstado(EstadoConfiteria.DISPONIBLE);
                comestible.setImagen("mi");
                Confiteria comida = administradorServicio.crearComestible(comestible);
                comestibles.add(comida);
                comestible = new Confiteria();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de comestible exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            } else {
                administradorServicio.actualizarComestible(comestible);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Comestible actualizado");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public void eliminarComestibles() {
        try {
            for (Confiteria comida : comestiblesSeleccionados) {
                administradorServicio.eliminarComestible(comida.getId());
                comestibles.remove(comestible);
            }
            comestiblesSeleccionados.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Comestible eliminado");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public String getTextoBtnBorrar() {
        if (comestiblesSeleccionados.size() == 0) {
            return "Borrar";
        } else {
            return comestiblesSeleccionados.size() == 1 ? "Borrar 1 elemento" : "Borrar " + comestiblesSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar comestible" : "Crear comestible";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.comestible = new Confiteria();
        editar = false;
    }

    public void seleccionarComestible(Confiteria comestible) {
        this.comestible = comestible;
        editar = true;
    }
}
