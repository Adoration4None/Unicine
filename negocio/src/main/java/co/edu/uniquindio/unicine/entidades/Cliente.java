package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Cliente extends Persona implements Serializable {
    // Relaciones ---------------------------------------------------------------------------------------
    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Compra> compras;

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Cupon> cupones;

    // Constructor --------------------------------------------------------------------------------------
    public Cliente(String cedula, String nombreCompleto, String email, String contrasena) {
        super(cedula, nombreCompleto, email, contrasena);
    }
}
