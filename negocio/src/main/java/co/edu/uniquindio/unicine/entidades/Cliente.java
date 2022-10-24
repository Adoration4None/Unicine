package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Cliente extends Persona implements Serializable {
    // Atributo extra
    private LocalDate fechaNacimiento;

    // Relaciones ---------------------------------------------------------------------------------------
    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Compra> compras;

    @ManyToMany(mappedBy = "clientes")
    @ToString.Exclude
    private List<Cupon> cupones;

    // Constructor --------------------------------------------------------------------------------------
    @Builder
    public Cliente(String cedula, String nombreCompleto, String email, String contrasena) {
        super(cedula, nombreCompleto, email, contrasena);
    }
}
