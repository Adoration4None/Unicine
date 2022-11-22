package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.EstadoConfiteria;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class ComestibleBean implements Serializable {
    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter
    @Setter
    private Confiteria comestible;

    @Getter
    @Setter
    private List<Confiteria> comestibles;

    @Getter
    @Setter
    private List<Confiteria> comestiblesSeleccionados;

    @Getter
    @Setter
    private EstadoConfiteria[] estados;

    private Map<String, String> imagenComida;

    private Boolean editar;

    public ComestibleBean(AdministradorServicio administradorServicio) {
        this.administradorServicio = administradorServicio;
    }

    @PostConstruct
    public void init() {
        estados = EstadoConfiteria.values();
        editar = false;
        comestible = new Confiteria();
        comestibles = administradorServicio.listarConfiteria();
        comestiblesSeleccionados = new ArrayList<>();
        imagenComida = new HashMap<>();
    }

    public void crearComestible() {
        try {
            if (!editar) {
                if(imagenComida == null || imagenComida.isEmpty()) {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe asignar una imagen");
                    PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
                }
                comestible.setImagen(imagenComida);
                Confiteria comida = administradorServicio.crearComestible(comestible);
                comestibles.add(comida);
                comestible = new Confiteria();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de comestible exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            } else {
                if(imagenComida != null && !imagenComida.isEmpty()) {
                    comestible.setImagen(imagenComida);
                }
                administradorServicio.actualizarComestible(comestible);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Comestible actualizado");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public void eliminarComestibles() {
        try {
            for (Confiteria comida : comestiblesSeleccionados) {
                administradorServicio.eliminarComestible(comida.getId());
                comestibles.remove(comestible);
            }
            comestiblesSeleccionados.clear();
            comestibles = administradorServicio.listarConfiteria();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Comestible eliminado");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "No se puede eliminar el comestible porque está asociado a otro objeto");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public String getTextoBtnBorrar() {
        if (comestiblesSeleccionados.size() == 0) {
            return "Borrar";
        } else {
            return comestiblesSeleccionados.size() == 1 ? "Borrar 1 elemento" : "Borrar " + comestiblesSeleccionados.size() + " elementos";
        }
    }

    public void subirImagen(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "confiteria");
            imagenComida.clear();
            imagenComida.put( resultado.get("public_id").toString(), resultado.get("url").toString() );
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
