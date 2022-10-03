package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

    // Constructor ----------------------------------------------------------------
    public Sala(Integer cantidadSillas, TipoSala tipo, Teatro teatro) {
        this.cantidadSillas = cantidadSillas;
        this.tipo = tipo;
        this.teatro = teatro;

        this.estado = EstadoSala.DISPONIBLE;
        this.sillas = crearSillasSala(cantidadSillas, sillas, 0);
    }

    /**
     *
     * @param cantidadSillas
     * @param sillas
     * @param i
     * @return
     */
    private List<Silla> crearSillasSala(Integer cantidadSillas, List<Silla> sillas, int i) {
        Silla nueva;

        if(i < cantidadSillas) {
            nueva = new Silla(this);
            sillas.add(nueva);

            return crearSillasSala(cantidadSillas, sillas, ++i);
        }

        return sillas;
    }
}
