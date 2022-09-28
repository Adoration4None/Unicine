package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
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
    private String nombre;

    // Relacion ----------------------------------------------------------
    @ManyToMany(mappedBy = "generos")
    private List<Pelicula> peliculas;
}
