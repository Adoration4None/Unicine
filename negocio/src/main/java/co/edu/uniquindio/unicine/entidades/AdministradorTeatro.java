package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class AdministradorTeatro extends Persona implements Serializable {
    // Relacion -----------------------------------------------------------------------------------------
    @OneToOne(mappedBy = "administrador")
    @ToString.Exclude
    private Teatro teatro;

    // Constructor --------------------------------------------------------------------------------------
    public AdministradorTeatro(String cedula, String nombreCompleto, String email, String contrasena) {
        super(cedula, nombreCompleto, email, contrasena);
    }
}
