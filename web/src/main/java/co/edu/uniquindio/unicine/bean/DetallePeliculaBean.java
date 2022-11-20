package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class DetallePeliculaBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['mov']}")
    private String idPelicula;

    @Value(value = "#{seguridadBean.ciudad.id}")
    private Integer idCiudad;

    @Getter @Setter
    private Pelicula pelicula;

    @Getter @Setter
    private List<Teatro> teatrosPeliculaCiudad;

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Funcion> funcionesPelicula;

    //luego lo borro
    @Getter @Setter
    private List<Pelicula> peliculasSeleccionadas;

    @PostConstruct
    public void init() {

        if(idPelicula != null) {
            try {
                pelicula = administradorServicio.obtenerPelicula( Integer.valueOf(idPelicula) );
                funcionesPelicula = clienteServicio.obtenerFuncionesPelicula( Integer.valueOf(idPelicula) );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String comprar(Funcion funcion) {
        if(funcion != null) {
            return "/cliente/compra.xhtml?faces-redirect=true&amp;func=" + funcion.getId();
        }

        return "";
    }
}
