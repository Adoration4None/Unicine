package co.edu.uniquindio.unicine.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @MapKeyColumn(name = "publicIdImagen")
    @Column(name = "urlImagen")
    @CollectionTable(name = "pelicula_imagen")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, String> imagen = new HashMap<>();

    // Relaciones -------------------------------------------------------
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Genero> generos = new ArrayList<>();

    @OneToMany(mappedBy = "pelicula")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @JsonIgnore
    private List<Funcion> funciones = new ArrayList<>();

    // Metodos ---------------------------------------------------------

    @JsonIgnore
    public List<String> getNombresGeneros() {
        List<String> nombres = new ArrayList<>();
        generos.forEach( (g) -> nombres.add(g.getNombre()) );

        return nombres;
    }

    @JsonIgnore
    public String getImagenPrincipal() {
        if(imagen == null || imagen.isEmpty()) {
            return "https://res.cloudinary.com/dheuspgiq/image/upload/v1668993640/unicine/film_bbmiy0.png";
        }

        String primera = imagen.keySet().toArray()[0].toString();
        return imagen.get(primera);
    }
}
