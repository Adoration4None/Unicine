package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(nullable = false)
    private Float precio;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoFuncion estado;

    // Relaciones -----------------------------------------------------------------
    @OneToMany(mappedBy = "funcion")
    @ToString.Exclude
    private List<Compra> compras;

    @ManyToOne
    private Pelicula pelicula;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Horario horario;
}
