package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Confiteria implements Serializable {
    // Atributos ----------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @MapKeyColumn(name = "publicIdImagen")
    @Column(name = "urlImagen")
    @CollectionTable(name = "comestible_imagen")
    private Map<String, String> imagen = new HashMap<>();

    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoConfiteria estado;

    // Relacion -------------------------------------------------------------
    @OneToMany(mappedBy = "comestible")
    private List<CompraConfiteria> comprasConfiteria = new ArrayList<>();

    // Constructor ----------------------------------------------------------
    public Confiteria(String nombre, Float precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getImagenPrincipal() {
        if(imagen == null || imagen.isEmpty()) {
            return "https://res.cloudinary.com/dheuspgiq/image/upload/v1669090670/unicine/fast-food_zfiwn7.png";
        }

        String primera = imagen.keySet().toArray()[0].toString();
        return imagen.get(primera);
    }
}
