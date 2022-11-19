package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class AdministradorTeatro extends Persona implements Serializable {

    // Relaciones -----------------------------------------------------------------------------------------
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Teatro> teatros = new ArrayList<>();

    @OneToOne
    private Ciudad ciudad;

    // Constructor ----------------------------------------------------------------------------------------
    public AdministradorTeatro(String cedula, String nombreCompleto, String email, String contrasena) {
        super(cedula, nombreCompleto, email, contrasena);
    }
}
