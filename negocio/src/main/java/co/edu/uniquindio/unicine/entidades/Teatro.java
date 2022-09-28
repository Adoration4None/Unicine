package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Teatro implements Serializable {
    // Atributos -------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 20, nullable = false)
    private String nombre;

    @Column(length = 20, nullable = false)
    private String direccion;

    // Relaciones -------------------------------------------------------------------
    @ManyToOne
    private Ciudad ciudad;

    @OneToOne
    private AdministradorTeatro administrador;

    @OneToMany(mappedBy = "teatro")
    private List<Sala> salas;
}
