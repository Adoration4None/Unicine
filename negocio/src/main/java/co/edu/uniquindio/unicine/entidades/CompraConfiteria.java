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
public class CompraConfiteria implements Serializable {
    // Atributos -----------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private Float precio;

    @Column(nullable = false)
    @PositiveOrZero
    @NonNull
    private Integer unidadesCompradas;

    // Relaciones ------------------------------------------------------------------------------------------
    @ManyToOne
    @NonNull
    private Compra compra;

    @ManyToOne
    @NonNull
    private Confiteria comestible;

    // Metodos ---------------------------------------------------------------------------------------------

    public void actualizarUnidades() {
        comestible.setUnidades(comestible.getUnidades() - unidadesCompradas);
    }
}
