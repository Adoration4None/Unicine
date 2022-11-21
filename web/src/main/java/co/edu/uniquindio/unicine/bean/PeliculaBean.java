package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

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
    }
    public void crearPelicula(){
        try {
            if(!editar){
//                pelicula.setImagen("miau");
                Pelicula peli = administradorServicio.crearPelicula(pelicula);
                peliculas.add(peli);
                pelicula = new Pelicula();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de pelicula exitoso");
                PrimeFaces.current().dialog().showMessageDynamic(facesMsg);
            }
            else{
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
