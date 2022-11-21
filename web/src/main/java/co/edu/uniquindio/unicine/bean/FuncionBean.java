package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class FuncionBean implements Serializable {
    @Autowired
    private AdminTeatroServicio administradorTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Value(value = "#{seguridadBean.ciudad.id}")
    private Integer idCiudad;

    @Getter
    @Setter
    private Funcion funcion;

    @Getter
    @Setter
    private List<Funcion> funciones;

    @Getter
    @Setter
    private List<Funcion> funcionesSeleccionadas;

    @Getter
    @Setter
    private List<Horario> horarios;

    @Getter
    @Setter
    private List<Sala> salas;

    @Getter
    @Setter
    private List<Pelicula> peliculas;

    private Boolean editar;

    @Getter
    @Setter
    private TipoFuncion[] tipos;

    public FuncionBean(AdminTeatroServicio administradorTeatroServicio) {
        this.administradorTeatroServicio = administradorTeatroServicio;
    }

    @PostConstruct
    public void init() {
        tipos = TipoFuncion.values();
        editar = false;
        funcion = new Funcion();
        peliculas = administradorServicio.listarPeliculas();
        try {
            salas = administradorTeatroServicio.obtenerSalasCiudad(idCiudad);
            funciones = administradorTeatroServicio.obtenerFuncionesCiudad(idCiudad);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        horarios = administradorTeatroServicio.listarHorarios();
        funcionesSeleccionadas = new ArrayList<>();
    }

    public void crearFuncion() {
        try {
            if (!editar) {
                Funcion fun = administradorTeatroServicio.crearFuncion(funcion);
                funciones.add(fun);
                funcion = new Funcion();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de funcion exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            } else {
                administradorTeatroServicio.actualizarFuncion(funcion);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Funcion actualizada");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public void eliminarFunciones() {
        try {
            for (Funcion fun: funcionesSeleccionadas) {
                administradorTeatroServicio.eliminarFuncion(fun.getId());
                funciones.remove(fun);
            }
            funcionesSeleccionadas.clear();
            funciones = administradorTeatroServicio.listarFunciones();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Funcion eliminada");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "No se puede eliminar la función porque está asociada a otro objeto");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public String getTextoBtnBorrar() {
        if (funcionesSeleccionadas.size() == 0) {
            return "Borrar";
        } else {
            return funcionesSeleccionadas.size() == 1 ? "Borrar 1 elemento" : "Borrar " + funcionesSeleccionadas.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar funcion" : "Crear funcion";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.funcion = new Funcion();
        editar = false;
    }

    public void seleccionarFuncion(Funcion funcion) {
        this.funcion = funcion;
        editar = true;
    }
}
