package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrada implements Serializable {
    private static final Float PRECIO_BASE = 8000.0f;

    // Atributos --------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer filaAsiento;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer columnaAsiento;

    @Column(nullable = false)
    private Float precio = PRECIO_BASE;

    // Relaciones -------------------------------------------------------------------------------------------
    @ManyToOne
    @ToString.Exclude
    private Sala sala;

    @ManyToOne
    @ToString.Exclude
    private Compra compra;

    @Builder
    public Entrada(@NonNull Integer filaAsiento, @NonNull Integer columnaAsiento) {
        this.filaAsiento = filaAsiento;
        this.columnaAsiento = columnaAsiento;
    }

    public Float calcularPrecio() {
        if( sala.getTipo() == TipoSala.SALA_3D ) precio += (precio * 0.25f);
        if( sala.getTipo() == TipoSala.SALA_XD ) precio += (precio * 0.5f);

        return precio;
    }
}
