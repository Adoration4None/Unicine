package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CuponCliente implements Serializable {
    // Atributos ------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoCupon estado = EstadoCupon.DISPONIBLE;

    // Relaciones -----------------------------------------------------------------------------------
    @ManyToOne
    @NonNull
    private Cliente cliente;

    @ManyToOne
    @NonNull
    private Cupon cupon;

    @OneToOne
    private Compra compra;

    // Metodos --------------------------------------------------------------------------------------

    public float obtenerPorcentajeDescuento() {
        return cupon.getPorcentajeDescuento();
    }
}
