package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrada implements Serializable {
    // Atributos --------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    @PositiveOrZero
    @NonNull
    private Integer filaAsiento;

    @Column(nullable = false)
    @PositiveOrZero
    @NonNull
    private Integer columnaAsiento;

    // Relaciones -------------------------------------------------------------------------------------------
    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Compra compra;
}
