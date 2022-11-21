package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
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
public class PeliculaBean implements Serializable {
    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter
    @Setter
    private Pelicula pelicula;

    @Getter
    @Setter
    private Boolean editar;

    @Getter @Setter
    private List<Pelicula> peliculasSeleccionadas;

    @Getter @Setter
    private EstadoPelicula[] estados;

    @Getter @Setter
    private List<Genero> generos;

    @Getter @Setter
    private List<Pelicula> peliculas;

    private Map<String, String> imagenPelicula;

    public PeliculaBean(AdministradorServicio administradorServicio){
        this.administradorServicio = administradorServicio;
    }
    @PostConstruct
    public void init(){
        generos = administradorServicio.obtenerGeneros();
        estados = EstadoPelicula.values();
        pelicula = new Pelicula();
        editar = false;
        peliculasSeleccionadas = new ArrayList<>();
        peliculas = administradorServicio.listarPeliculas();
        imagenPelicula = new HashMap<>();
    }
    public void crearPelicula(){
        try {
            if(!editar){
                if(imagenPelicula == null) {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Debe asignar una imagen");
                    PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
                }
                pelicula.setImagen(imagenPelicula);
                Pelicula peli = administradorServicio.crearPelicula(pelicula);
                peliculas.add(peli);
                pelicula = new Pelicula();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de pelicula exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }
            else{
                if(imagenPelicula != null && !imagenPelicula.isEmpty()) {
                    pelicula.setImagen(imagenPelicula);
                }
                administradorServicio.actualizarPelicula(pelicula);
                peliculas = administradorServicio.listarPeliculas();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Pelicula actualizada");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public void subirImagen(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "peliculas");
            imagenPelicula.clear();
            imagenPelicula.put( resultado.get("public_id").toString(), resultado.get("url").toString() );
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

    public void eliminarPelicula(){
        try{
            for (Pelicula p: peliculasSeleccionadas){
                administradorServicio.eliminarPelicula(p.getId());
                peliculas.remove(p);
            }
            peliculasSeleccionadas.clear();
            peliculas = administradorServicio.listarPeliculas();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Pelicula eliminada");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "No se puede eliminar la película porque está asociada a otro objeto");
            PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
        }
    }

    public String getTextoBtnBorrar(){
        if(peliculasSeleccionadas.size()==0){
            return "Borrar";
        }else{
            return peliculasSeleccionadas.size() ==1 ? "Borrar 1 elemento" : "Borrar "+peliculasSeleccionadas.size() +" elementos";
        }
    }

    public void seleccionarPelicula(Pelicula peli){
        this.pelicula = peli;
        editar = true;
    }

    public void botonAgregarEditar() {
        this.pelicula = new Pelicula();
        editar = false;
    }

    public String getMensajeCrear() {
        return editar ? "Actualizar pelicula" : "Crear pelicula";
    }

    public String getMensaje2Crear() {
        return editar ? "Actualizar" : "Crear";
    }
}
