package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    private TipoFuncion tipo;

    @Column(nullable = false)
    @NonNull
    private Float precio;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @NonNull
    private EstadoFuncion estado;

    // Relaciones -----------------------------------------------------------------
    @OneToMany(mappedBy = "funcion")
    @ToString.Exclude
    private List<Compra> compras;

    @ManyToOne
    @NonNull
    private Pelicula pelicula;

    @ManyToOne
    @NonNull
    private Sala sala;

    @ManyToOne
    @NonNull
    private Horario horario;

}
