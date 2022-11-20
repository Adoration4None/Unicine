package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.entidades.EstadoCupon;
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
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CuponesClienteBean implements Serializable {
    @Autowired
    private ClienteServicio clienteServicio;

    @Value(value = "#{seguridadBean.cliente}")
    private Cliente cliente;

    @Getter @Setter
    private List<CuponCliente> cuponesCliente;

    @Getter @Setter
    private String nombreCupon;

    @PostConstruct
    public void init() {
        try {
            if(cliente != null) {
                cuponesCliente = clienteServicio.obtenerCuponesCliente(cliente);
                cuponesCliente = actualizarCuponesCliente(cuponesCliente);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<CuponCliente> actualizarCuponesCliente(List<CuponCliente> cuponesCliente) {
        List<CuponCliente> listaActualizada = new ArrayList<>();

        try {
            for(CuponCliente c : cuponesCliente) {
                if( clienteServicio.cuponVencido(c.getCupon()) ) {
                    c.setEstado(EstadoCupon.VENCIDO);
                    clienteServicio.actualizarCuponCliente(c);
                }
                listaActualizada.add(c);
            }
        } catch (Exception e) {
            mostrarError(e);
        }

        return listaActualizada;
    }

    public void agregarCupon() {
        try {
            if(nombreCupon != null && !nombreCupon.isEmpty()) {
                clienteServicio.agregarCupon(nombreCupon, cliente);
                cuponesCliente = clienteServicio.obtenerCuponesCliente(cliente);
            }
            else {
                mostrarError( new Exception("Ingrese un nombre valido") );
            }
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        PrimeFaces.current().dialog().showMessageDynamic(fm);
    }
}
