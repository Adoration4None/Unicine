package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Persona implements Serializable {
    // Atributos -----------------------------------------------------------------------------------------
    @Id
    @EqualsAndHashCode.Include
    @Length(max = 10)
    @Column(length = 10)
    private String cedula;

    @Length(max = 60)
    @Column(length = 60, nullable = false)
    private String nombreCompleto;

    @Length(max = 50)
    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Lob
    @ToString.Exclude
    @Column(nullable = false)
    private String contrasena;

    @Lob
    private String imagenPerfil;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoPersona estado = EstadoPersona.INACTIVO;

    // Constructor ------------------------------------------------------------------------------------
    public Persona(String cedula, String nombreCompleto, String email, String contrasena) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasena = contrasena;
    }

}
