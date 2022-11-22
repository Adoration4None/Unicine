package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

    @Column(length = 20, nullable = false, unique = true)
    @NonNull
    private String nombre;

    // Relacion ----------------------------------------------------------
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "generos")
    private List<Pelicula> peliculas = new ArrayList<>();
}
