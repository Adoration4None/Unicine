package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.EstadoPersona;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class AdminTeatroBean implements Serializable {
    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter
    @Setter
    @Value(value="#{seguridadBean.administradorTeatro}")
    private AdministradorTeatro administradorTeatro;

    @Getter
    @Setter
    private AdministradorTeatro administrador;

    @Getter
    @Setter
    private List<AdministradorTeatro> administradores;

    @Getter
    @Setter
    private List<AdministradorTeatro> administradoresSeleccionados;

    @Getter
    @Setter
    private EstadoPersona[] estados;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    private Boolean editar;

    private Map<String, String> imagenPerfil;

    public AdminTeatroBean(AdministradorServicio administradorServicio) {
        this.administradorServicio = administradorServicio;
    }

    @PostConstruct
    public void init() {
        ciudades = administradorServicio.listarCiudades();
        estados = EstadoPersona.values();
        editar = false;
        administrador = new AdministradorTeatro();
        administradores = administradorServicio.listarAdministradores();
        administradoresSeleccionados = new ArrayList<>();
        imagenPerfil = new HashMap<>();
    }

    public void crearAdmin() {
        try {
            if (!editar) {
                administrador.setImagenPerfil(imagenPerfil);
                AdministradorTeatro admin = administradorServicio.crearAdministrador(administrador);
                administradores.add(admin);
                administrador = new AdministradorTeatro();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de administrador exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            } else {
                if(imagenPerfil != null && !imagenPerfil.isEmpty()) {
                    administrador.setImagenPerfil(imagenPerfil);
                }
                administradorServicio.actualizarAdministrador(administrador);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Administrador actualizado");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }

        } catch (Exception e) {
            if(e.getMessage().contains("administrador_teatro.UK_540p4087vlp4brxqmlc8dqtlf")){
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El email ya existe en la base de datos");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }
        }
    }

    public void eliminarAdmins() {
        try {
            for (AdministradorTeatro admin : administradoresSeleccionados) {
                administradorServicio.eliminarAdministrador(admin.getCedula());
                administradores.remove(admin);
            }
            administradoresSeleccionados.clear();
            administradores = administradorServicio.listarAdministradores();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Administrador eliminado");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "No se puede eliminar el administrador de teatro porque está asociado a otro objeto");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public void subirImagen(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "administradores");
            imagenPerfil.clear();
            imagenPerfil.put( resultado.get("public_id").toString(), resultado.get("url").toString() );
        } catch(Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }

    }

    private File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();

        return file;
    }

    public String getTextoBtnBorrar() {
        if (administradoresSeleccionados.size() == 0) {
            return "Borrar";
        } else {
            return administradoresSeleccionados.size() == 1 ? "Borrar 1 elemento" : "Borrar " + administradoresSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar administrador" : "Crear administrador";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }

    public void botonAgregarEditar() {
        this.administrador = new AdministradorTeatro();
        editar = false;
    }

    public void seleccionarAdmin(AdministradorTeatro admin) {
        this.administrador = admin;
        editar = true;
    }

    public String gestionarCiudades() {
        return "/admin/ciudades?faces-redirect=true";
    }

    public String gestionarGeneros() {
        return "/admin/generos?faces-redirect=true";
    }

    public String gestionarHorarios() {
        return "/admin_teatro/horarios?faces-redirect=true";
    }
}
