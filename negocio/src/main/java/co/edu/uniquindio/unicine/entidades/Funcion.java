package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Funcion implements Serializable {
    // Atributos -----------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private TipoFuncion tipo;

    // Relaciones -----------------------------------------------------------------
    @OneToMany(mappedBy = "funcion")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList<>();

    @ManyToOne
    private Pelicula pelicula;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Horario horario;

    // Constructor -----------------------------------------------------------------
    @Builder
    public Funcion(TipoFuncion tipo, Pelicula pelicula, Sala sala, Horario horario) {
        this.tipo = tipo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.horario = horario;
    }

}
