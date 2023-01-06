package co.edu.uniquindio.unicine.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false)
    @NonNull
    private Integer filas;

    @Column(nullable = false)
    @NonNull
    private Integer numero;

    @Column(nullable = false)
    @NonNull
    private Integer columnas;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @NonNull
    private TipoSala tipo;

    // Relaciones ----------------------------------------------------------------
    @OneToMany(mappedBy = "sala")
    @ToString.Exclude
    @JsonIgnore
    private List<Funcion> funciones = new ArrayList<>();

    @OneToMany(mappedBy = "sala")
    @ToString.Exclude
    @JsonIgnore
    private List<Entrada> entradas = new ArrayList<>();

    @ManyToOne
    @NonNull
    private Teatro teatro;

}
