package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
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
public class HorarioBean implements Serializable {
    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter
    @Setter
    private Horario horario;

    @Getter
    @Setter
    private List<Horario> horarios;

    @Getter
    @Setter
    private List<Horario> horariosSeleccionados;

    private Boolean editar;

    public HorarioBean(AdminTeatroServicio adminTeatroServicio) {
        this.adminTeatroServicio = adminTeatroServicio;
    }

    @PostConstruct
    public void init() {
        editar = false;
        horario = new Horario();
        horarios = adminTeatroServicio.listarHorarios();
        horariosSeleccionados = new ArrayList<>();
    }

    public void crearHorario() {
        try {
            if (!editar) {
                Horario hora = adminTeatroServicio.crearHorario(horario);
                horarios.add(hora);
                horario = new Horario();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de horario exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            } else {
                adminTeatroServicio.actualizarHorario(horario);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Horario actualizado");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);        }
    }

    public void eliminarHorarios() {
        try {
            for (Horario hora : horariosSeleccionados) {
                adminTeatroServicio.eliminarHorario(hora.getId());
                horarios.remove(hora);
            }
            horariosSeleccionados.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Horario eliminado");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);        }
    }

    public String getTextoBtnBorrar() {
        if (horariosSeleccionados.size() == 0) {
            return "Borrar";
        } else {
            return horariosSeleccionados.size() == 1 ? "Borrar 1 elemento" : "Borrar " + horariosSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar horario" : "Crear horario";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.horario = new Horario();
        editar = false;
    }

    public void seleccionarHorario(Horario horario) {
        this.horario = horario;
        editar = true;
    }
}
