package co.edu.uniquindio.unicine.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Silla implements Serializable {
    // Atributo ------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relaciones ----------------------------------------------------
    @ManyToMany(mappedBy = "sillas")
    private List<Compra> compras;

    @ManyToOne
    private Sala sala;
}
