package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@ViewScoped
public class RegistroClienteBean implements Serializable {
    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private String nombresCliente;

    @Getter @Setter
    private String apellidosCliente;

    @Getter @Setter
    private String confirmacionContrasena;

    @Getter @Setter
    private boolean registroExitoso;

    private Map<String, String> imagenPerfil;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
        registroExitoso = false;
        imagenPerfil = new HashMap<>();
    }

    public void registrarCliente() {
        cliente.setNombreCompleto(nombresCliente + " " + apellidosCliente);

        if( cliente.getContrasena().equals(confirmacionContrasena) ) {
            try {
                cliente.setImagenPerfil(imagenPerfil);
                clienteServicio.registrar(cliente);
                registroExitoso = true;

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", "Activa tu cuenta ingresando al enlace enviado a tu correo electronico");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro", fm);
            }
            catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_registro", fm);
            }
        }
        else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden");
            FacesContext.getCurrentInstance().addMessage("mensaje_registro", fm);
        }
    }

    public void subirImagen(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "usuarios");
            imagenPerfil.clear();
            imagenPerfil.put( resultado.get("public_id").toString(), resultado.get("url").toString() );
        } catch(Exception e) {
            mostrarError(e);
        }

    }

    private File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();

        return file;
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        PrimeFaces.current().dialog().showMessageDynamic(fm);
    }

}
