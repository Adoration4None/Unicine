package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ciudad implements Serializable {
    // Atributos ----------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 20, nullable = false)
    @NonNull
    private String nombre;

    @Column(length = 20, nullable = false)
    @NonNull
    private String departamento;

    // Relacion ------------------------------------------------------------
    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Teatro> teatros = new ArrayList<>();
}
