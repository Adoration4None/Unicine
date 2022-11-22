package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class DetalleCompraBean implements Serializable {
    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['b']}")
    private Integer idCompra;

    @Getter @Setter
    private Compra compra;

    @PostConstruct
    public void init() {
        if(idCompra != null) {
            try {
                compra = clienteServicio.obtenerCompra(idCompra);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
