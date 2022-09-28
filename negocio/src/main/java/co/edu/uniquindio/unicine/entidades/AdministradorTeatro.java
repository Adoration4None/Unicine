package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdministradorTeatro extends Persona implements Serializable {
    // Relacion -----------------------------------------------------------------------------------------
    @OneToOne(mappedBy = "administrador")
    private Teatro teatro;

    // Constructor --------------------------------------------------------------------------------------
    public AdministradorTeatro(String cedula, String nombreCompleto, String email, String contrasena) {
        super(cedula, nombreCompleto, email, contrasena);
    }
}
