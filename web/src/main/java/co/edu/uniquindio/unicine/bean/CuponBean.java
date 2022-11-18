package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cupon;
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
public class CuponBean implements Serializable {
    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter
    @Setter
    private Cupon cupon;

    @Getter
    @Setter
    private List<Cupon> cupones;

    @Getter
    @Setter
    private List<Cupon> cuponesSeleccionados;

    private Boolean editar;

    public CuponBean(AdministradorServicio administradorServicio) {
        this.administradorServicio = administradorServicio;
    }

    @PostConstruct
    public void init() {
        editar = false;
        cupon = new Cupon();
        cupones = administradorServicio.listarCupones();
        cuponesSeleccionados = new ArrayList<>();
    }

    public void crearCupon() {
        try {
            if (!editar) {
                Cupon cup = administradorServicio.crearCupon(cupon);
                cupones.add(cup);
                cupon = new Cupon();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de cupon exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            } else {
                administradorServicio.actualizarCupon(cupon);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon actualizado");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public void eliminarCupones() {
        try {
            for (Cupon cup : cuponesSeleccionados) {
                administradorServicio.eliminarCupon(cup.getId());
                cupones.remove(cup);
            }
            cuponesSeleccionados.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon eliminado");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public String getTextoBtnBorrar() {
        if (cuponesSeleccionados.size() == 0) {
            return "Borrar";
        } else {
            return cuponesSeleccionados.size() == 1 ? "Borrar 1 elemento" : "Borrar " + cuponesSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar cupon" : "Crear cupon";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.cupon = new Cupon();
        editar = false;
    }

    public void seleccionarCupon(Cupon cupon) {
        this.cupon = cupon;
        editar = true;
    }
}
