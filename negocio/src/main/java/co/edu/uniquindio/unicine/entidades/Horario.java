package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Horario implements Serializable {
    // Atributos ---------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private LocalDate fecha;

    @Column(nullable = false)
    @NonNull
    private LocalTime hora;

    // Relacion -----------------------------------------------------------------
    @OneToMany(mappedBy = "horario")
    @ToString.Exclude
    private List<Funcion> funciones = new ArrayList<>();

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }
}
