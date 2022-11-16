package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioBean implements Serializable {
    @Autowired
    private ClienteServicio clienteServicio;

    @Getter
    @Setter
    private Cliente cliente, cliente1, cliente2, cliente3;

    @Getter
    @Setter
    private List<Cliente> clientes;

    @Getter
    @Setter
    private List<Cliente> clientesSeleccionados;

    @Getter
    @Setter
    private String confirmacion;

    public UsuarioBean(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @PostConstruct
    public void init(){
        cliente = new Cliente();
        clientes = new ArrayList<>();
        clientes = clienteServicio.listarClientes();
    }

    public void registrarUsuario(){
        try {
            if(cliente.getContrasena().equals(confirmacion)){
                Cliente clientazo = clienteServicio.registrar(cliente);
                clientes.add(clientazo);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
    }

    public void eliminarCliente(){
        try{
            for (Cliente c: clientesSeleccionados){
                clienteServicio.eliminar(c.getCedula());
            }
        } catch (Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
    }
}
