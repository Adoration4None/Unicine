package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CompraBean implements Serializable {
    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Value("#{param['city']}")
    private String idCiudad;

    @Value("#{param['func']}")
    private String idFuncion;

    @Getter @Setter
    private List<Integer> listaAsientosSala;

    @Getter @Setter
    private List<Integer> asientosEscogidos;

    @Getter @Setter
    private Funcion funcion;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private Compra compra;

    @PostConstruct
    public void init() {
        if(idFuncion != null && !idFuncion.isEmpty()) {
            try {
                funcion = adminTeatroServicio.obtenerFuncion( Integer.valueOf(idFuncion) );
                listaAsientosSala = llenarListaAsientos( funcion.getSala().getCantidadSillas() );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<Integer> llenarListaAsientos(Integer cantidadSillas) {
        List<Integer> lista = new ArrayList<>();

        for(int i = 1; i <= cantidadSillas; i++) {
            lista.add(i);
        }

        return lista;
    }
}
