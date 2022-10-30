package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Normalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genero {
    // Atributos ---------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String nombre;

    // Relacion ----------------------------------------------------------
    @ToString.Exclude
    @ManyToMany(mappedBy = "generos")
    private List<Pelicula> peliculas = new ArrayList<>();
}
