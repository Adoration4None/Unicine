package co.edu.uniquindio.unicine.bean;

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

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter
    @Setter
    private Pelicula pelicula;

    @Getter
    @Setter
    private String mensajeExito;

    @Getter
    @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private String busqueda;

    private boolean editar;
    @Getter
    @Setter
    private String idCiudad;

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
        peliculasBusqueda = new ArrayList<>();
        peliculas = administradorServicio.listarPeliculas();
    }
    public String crearPelicula(){
        try {
            pelicula.setEstado(EstadoPelicula.PREVENTA);
            administradorServicio.crearPelicula(pelicula);
            //aqui debe de ir un pelicula=new Pelicula(); para actualizar el datatable
            mensajeExito= "Felicitaciones, has creado una pelicula exitosamente \n "+pelicula.toString();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro de pelicula exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            return "pagina_exito2?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
        return null;
    }

    public void showMessage(){
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "pelicula creada exitosamente", pelicula.toString());
        FacesContext.getCurrentInstance().addMessage("mensaje_exito", facesMsg);
    }

    public String buscarPelicula() {
        if (busqueda != null && !busqueda.isEmpty()) {
            try {
                peliculasBusqueda = clienteServicio.buscarPeliculas(busqueda, Integer.valueOf(idCiudad));
                //solo se puede utilizar un ? por url, entonces se utiliza un & que va acompañado de amp; para que Java lo codifique bien, q es solo una "variable" con la busqueda
                return "/busqueda_resultados?faces-redirect=true&amp;city="+busqueda;
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }

        }
        return "";
    }

    public void eliminarPelicula(){
        try{
            for (Pelicula p: peliculasSeleccionadas){
               administradorServicio.eliminarPelicula(p.getNombre());
               peliculas.remove(p);
            }
            peliculasSeleccionadas.clear();
        } catch (Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
    }

    public String getTextoBtnBorrar(){
        if(peliculasSeleccionadas.size()==0){
            return "Borrar";
        }else{
            return peliculasSeleccionadas.size() ==1 ? "Borrar 1 elemento" : "Borrar "+peliculasSeleccionadas.size() +" elementos";
        }
    }

    public void seleccionarPelicula(Pelicula p){
        this.pelicula = pelicula;
        editar=true;

    }


}
