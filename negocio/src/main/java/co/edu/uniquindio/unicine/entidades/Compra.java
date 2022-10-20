package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra implements Serializable {
    // Atributos -----------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NonNull
    private MetodoPago metodoPago;

    @Column(nullable = false)
    @NonNull
    private Float valorTotal;

    // Relaciones ------------------------------------------------------
    @ManyToOne
    @NonNull
    private Cliente cliente;

    @OneToMany(mappedBy = "compra")
    private List<CompraConfiteria> comprasConfiteria;

    @OneToOne
    private Cupon cupon;

    @ManyToOne
    @NonNull
    private Funcion funcion;

    @OneToMany(mappedBy = "compra")
    @NonNull
    private List<Entrada> entradas;

}
