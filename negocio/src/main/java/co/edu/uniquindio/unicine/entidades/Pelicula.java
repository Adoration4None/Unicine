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
public class Pelicula implements Serializable {
    // Atributos ---------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    @NonNull
    private String nombre;

    @Lob
    @Column(nullable = false)
    @NonNull
    private String imagen;

    @Lob
    @Column(nullable = false)
    @NonNull
    private String trailer;

    @Lob
    @Column(nullable = false)
    @NonNull
    private String sinopsis;

    @Lob
    @Column(nullable = false)
    @NonNull
    private String reparto;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @NonNull
    private EstadoPelicula estado;

    // Relaciones -------------------------------------------------------
    @ManyToMany
    private List<Genero> generos = new ArrayList<>();

    @OneToMany(mappedBy = "pelicula")
    @ToString.Exclude
    private List<Funcion> funciones = new ArrayList<>();

    // Metodos ---------------------------------------------------------

    public List<String> getNombresGeneros() {
        List<String> nombres = new ArrayList<>();
        generos.forEach( (g) -> nombres.add(g.getNombre()) );

        return nombres;
    }
}
