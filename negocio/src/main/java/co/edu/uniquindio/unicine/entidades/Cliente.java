package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String cedula;

    @Column(length = 50, nullable = false)
    private String nombreCompleto;

    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    private String imagenPerfil;

    @ElementCollection
    @Column(length = 10)
    private Map<String, String> telefonos;
}
