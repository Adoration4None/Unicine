package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pelicula implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 50)
    private String nombre;

    @Column(nullable = false)
    private String imagen;

    @Column(nullable = false)
    private String trailer;

    @Column(nullable = false)
    private String sinopsis;

    @Column(nullable = false)
    private String reparto;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoPelicula estado;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Column(length = 20, nullable = false)
    private List<Genero> generos;
}
