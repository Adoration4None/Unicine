package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupon implements Serializable {
    // Atributos -----------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private Float valorDescuento;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CriterioCupon criterio;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoCupon estado;

    // Relaciones -------------------------------------------------------
    @ManyToOne
    private Cliente cliente;

    @OneToOne(mappedBy = "cupon")
    private Compra compra;

    // Constructor -------------------------------------------------------
    public Cupon(String nombre, Float valorDescuento, LocalDate fechaVencimiento, String descripcion,
                 CriterioCupon criterio) {
        this.nombre = nombre;
        this.valorDescuento = valorDescuento;
        this.fechaVencimiento = fechaVencimiento;
        this.descripcion = descripcion;
        this.criterio = criterio;

        this.estado = EstadoCupon.DISPONIBLE;
    }

}