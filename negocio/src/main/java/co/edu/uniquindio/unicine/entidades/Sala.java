package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sala implements Serializable {
    // Atributos ----------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private Integer cantidadSillas;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoSala estado;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoSala tipo;

    // Relaciones ----------------------------------------------------------------
    @OneToMany(mappedBy = "sala")
    @ToString.Exclude
    private List<Funcion> funciones;

    @OneToMany(mappedBy = "sala")
    @ToString.Exclude
    private List<Silla> sillas;

    @ManyToOne
    private Teatro teatro;
}
