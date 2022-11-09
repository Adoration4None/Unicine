package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Float porcentajeDescuento;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CriterioCupon criterio;

    // Relaciones -------------------------------------------------------
    @OneToMany(mappedBy = "cupon")
    @ToString.Exclude
    private List<CuponCliente> clientesCupon = new ArrayList<>();

    // Constructor -------------------------------------------------------
    @Builder
    public Cupon(String nombre, Float porcentajeDescuento, LocalDate fechaVencimiento, String descripcion,
                 CriterioCupon criterio) {
        this.nombre = nombre;
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaVencimiento = fechaVencimiento;
        this.descripcion = descripcion;
        this.criterio = criterio;
    }

    // Metodos ------------------------------------------------------------
    public void agregarCuponCliente(CuponCliente cuponCliente) {
        clientesCupon.add(cuponCliente);
    }
}
