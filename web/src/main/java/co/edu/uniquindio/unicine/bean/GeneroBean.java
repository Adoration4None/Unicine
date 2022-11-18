package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
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
public class GeneroBean implements Serializable {
    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter
    @Setter
    private Genero genero;

    @Getter
    @Setter
    private List<Genero> generos;

    @Getter
    @Setter
    private List<Genero> generosSeleccionados;

    private Boolean editar;

    public GeneroBean(AdministradorServicio administradorServicio) {
        this.administradorServicio = administradorServicio;
    }

    @PostConstruct
    public void init() {
        editar = false;
        genero = new Genero();
        generos = administradorServicio.obtenerGeneros();
        generosSeleccionados = new ArrayList<>();
    }

    public void crearGenero() {
        try {
            if (!editar) {
                Genero gen = administradorServicio.crearGenero(genero);
                generos.add(gen);
                genero = new Genero();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de genero exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            } else {
                administradorServicio.actualizarGenero(genero);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Genero actualizado");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public void eliminarGeneros() {
        try {
            for (Genero gen : generosSeleccionados) {
                administradorServicio.eliminarGenero(gen.getId());
                generos.remove(gen);
            }
            generosSeleccionados.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Genero eliminado");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public String getTextoBtnBorrar() {
        if (generosSeleccionados.size() == 0) {
            return "Borrar";
        } else {
            return generosSeleccionados.size() == 1 ? "Borrar 1 elemento" : "Borrar " + generosSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar genero" : "Crear genero";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.genero = new Genero();
        editar = false;
    }

    public void seleccionarGenero(Genero genero) {
        this.genero = genero;
        editar = true;
    }
}
