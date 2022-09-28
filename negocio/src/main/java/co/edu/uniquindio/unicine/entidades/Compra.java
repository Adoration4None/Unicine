package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
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
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoPago;

    @Column(nullable = false)
    private Float valorTotal;

    // Relaciones ------------------------------------------------------
    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Confiteria> confiteria;

    @OneToOne
    private Cupon cupon;

    @ManyToOne
    private Funcion funcion;

    @ManyToMany
    private List<Silla> sillas;
}
