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
    @EqualsAndHashCode.Include
    @Column(length = 50)
    @NonNull
    private String nombre;

    @Column(nullable = false)
    @NonNull
    private String imagen;

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
    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    public List<String> getNombresGeneros() {
        List<String> nombres = new ArrayList<>();
        generos.forEach( (g) -> nombres.add(g.getNombre()) );

        return nombres;
    }
}
