package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    private String nombre;

    @Column(length = 20, nullable = false)
    @NonNull
    private String direccion;

    // Relaciones -------------------------------------------------------------------
    @ManyToOne
    @NonNull
    private Ciudad ciudad;

    @OneToOne
    @NonNull
    private AdministradorTeatro administrador;

    @OneToMany(mappedBy = "teatro")
    @ToString.Exclude
    private List<Sala> salas;
}
