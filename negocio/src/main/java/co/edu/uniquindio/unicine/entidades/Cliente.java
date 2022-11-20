package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Cliente extends Persona implements Serializable {
    // Atributos extra
    private LocalDate fechaNacimiento;

    @Length(max = 10)
    @Column(length = 10)
    private String telefonoCelular;

    @Length(max = 10)
    @Column(length = 10)
    private String telefonoFijo;

    @ElementCollection
    @Column(name = "anio")
    private List<Integer> aniosCelebrados;

    // Relaciones ---------------------------------------------------------------------------------------
    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<CuponCliente> cuponesCliente = new ArrayList<>();

    // Constructor --------------------------------------------------------------------------------------
    @Builder
    public Cliente(String cedula, String nombreCompleto, String email, String contrasena) {
        super(cedula, nombreCompleto, email, contrasena);
    }

    public void agregarAnioCelebrado(Integer anio) {
        aniosCelebrados.add(anio);
    }
}
