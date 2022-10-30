package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    private Integer cantidadSillas;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @NonNull
    private TipoSala tipo;

    // Relaciones ----------------------------------------------------------------
    @OneToMany(mappedBy = "sala")
    @ToString.Exclude
    private List<Funcion> funciones = new ArrayList<>();

    @OneToMany(mappedBy = "sala")
    @ToString.Exclude
    private List<Entrada> entradas = new ArrayList<>();

    @ManyToOne
    @NonNull
    private Teatro teatro;

}
