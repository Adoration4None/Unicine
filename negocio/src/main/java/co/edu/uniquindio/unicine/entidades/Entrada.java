package co.edu.uniquindio.unicine.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Float precio;

    @Column(nullable = false)
    private Float precioBase;

    // Relaciones -------------------------------------------------------------------------------------------
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Sala sala;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Compra compra;

    @Builder
    public Entrada(@NonNull Integer filaAsiento, @NonNull Integer columnaAsiento, @NonNull Float precioBase) {
        this.filaAsiento = filaAsiento;
        this.columnaAsiento = columnaAsiento;
        this.precioBase = precioBase;
    }

    public Float calcularPrecio() {
        this.precio = precioBase + getRecargo();
        return precio;
    }

    public float getRecargo() {
        if( sala.getTipo() == TipoSala.SALA_3D ) return precioBase * 0.25f;
        if( sala.getTipo() == TipoSala.SALA_XD ) return precioBase * 0.5f;

        return 0f;
    }
}
