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
    private Cliente cliente;

    @Getter
    @Setter
    private List<Cliente> clientes;

    @Getter
    @Setter
    private List<Cliente> clientesSeleccionados;

    @Getter
    @Setter
    private String confirmacion;

    private Boolean editar;

    public UsuarioBean(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @PostConstruct
    public void init(){
        editar=false;
        cliente = new Cliente();
        clientesSeleccionados = new ArrayList<>();
        clientes = clienteServicio.listarClientes();
    }

    public void registrarUsuario(){
        try {
            if(!editar){
                if(cliente.getContrasena().equals(confirmacion)){
                    Cliente clientazo = clienteServicio.registrar(cliente);
                    clientes.add(clientazo);
                    cliente = new Cliente();
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
                }
            }else{
                clienteServicio.actualizar(cliente);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cliente actualizado");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public void eliminarClientes(){
        try{
            for (Cliente c: clientesSeleccionados){
                clienteServicio.eliminar(c.getCedula());
                clientes.remove(c);
            }
            clientesSeleccionados.clear();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cliente eliminado");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        } catch (Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }

    public String getTextoBtnBorrar(){
        if(clientesSeleccionados.size()==0){
            return "Borrar";
        }else{
            return clientesSeleccionados.size() ==1 ? "Borrar 1 elemento" : "Borrar "+clientesSeleccionados.size() +" elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar teatro" : "Crear teatro";
    }

    public void botonAgregarEditar(){
        confirmacion="";
        this.cliente=new Cliente();
        editar=false;
    }

    public void seleccionarCliente(Cliente clienteC){
        this.cliente = clienteC;
        editar=true;
    }
}
