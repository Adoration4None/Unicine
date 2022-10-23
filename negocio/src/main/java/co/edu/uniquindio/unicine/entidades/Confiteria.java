package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Confiteria implements Serializable {
    // Atributos ----------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String imagen;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer unidades;

    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoConfiteria estado;

    // Relacion -------------------------------------------------------------
    @OneToMany(mappedBy = "comestible")
    private List<CompraConfiteria> comprasConfiteria;

    // Constructor ----------------------------------------------------------
    public Confiteria(String nombre, String imagen, Integer unidades, Float precio, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.unidades = unidades;
        this.precio = precio;
        this.descripcion = descripcion;

        this.estado = EstadoConfiteria.DISPONIBLE;
    }
}
