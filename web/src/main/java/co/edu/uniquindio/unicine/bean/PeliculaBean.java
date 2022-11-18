package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
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
import java.io.Serializable;
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
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private String busqueda;

    private Boolean editar;

    @Getter @Setter
    private List<Pelicula> peliculasBusqueda;

    @Getter @Setter
    private List<Pelicula> peliculasSeleccionadas;

    @Getter @Setter
    private List<Pelicula> peliculas;
    @PostConstruct
    public void init(){
        pelicula = new Pelicula();
        editar=false;
        peliculasSeleccionadas = new ArrayList<>();
        peliculas = administradorServicio.listarPeliculas();
    }
    public void crearPelicula(){
        try {
            if(!editar){
                Pelicula peli = administradorServicio.crearPelicula(pelicula);
                peliculas.add(peli);
                pelicula = new Pelicula();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de pelicula exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }
            else{
                administradorServicio.actualizarPelicula(pelicula);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Pelicula actualizada");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

//    public void showMessage(){
//        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "pelicula creada exitosamente", pelicula.toString());
//        FacesContext.getCurrentInstance().addMessage("mensaje_exito", facesMsg);
//    }

//    public String buscarPelicula() {
//        if (busqueda != null && !busqueda.isEmpty()) {
//            try {
//                peliculasBusqueda = clienteServicio.buscarPeliculas(busqueda, Integer.valueOf(idCiudad));
//                //solo se puede utilizar un ? por url, entonces se utiliza un & que va acompañado de amp; para que Java lo codifique bien, q es solo una "variable" con la busqueda
//                return "/busqueda_resultados?faces-redirect=true&amp;city="+busqueda;
//            } catch (Exception e) {
//                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
//                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
//            }
//
//        }
//        return "";
//    }

    public void eliminarPelicula(){
        try{
            for (Pelicula p: peliculasSeleccionadas){
               administradorServicio.eliminarPelicula(p.getNombre());
               peliculas.remove(p);
            }
            peliculasSeleccionadas.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Pelicula eliminada");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        } catch (Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public String getTextoBtnBorrar(){
        if(peliculasSeleccionadas.size()==0){
            return "Borrar";
        }else{
            return peliculasSeleccionadas.size() ==1 ? "Borrar 1 elemento" : "Borrar "+peliculasSeleccionadas.size() +" elementos";
        }
    }

    public void seleccionarPelicula(Pelicula pelicula){
        this.pelicula = pelicula;
        editar=true;
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
