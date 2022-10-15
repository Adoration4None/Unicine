package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Persona implements Serializable {
    // Atributos -----------------------------------------------------------------------------------------
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String cedula;

    @Column(length = 50, nullable = false)
    private String nombreCompleto;

    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @ToString.Exclude
    @Column(nullable = false)
    private String contrasena;

    private String imagenPerfil;

    @ElementCollection
    @Column(length = 10)
    private Map<String, String> telefonos;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoPersona estado;


    // Constructor ------------------------------------------------------------------------------------
    public Persona(String cedula, String nombreCompleto, String email, String contrasena) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasena = contrasena;

        this.telefonos = new HashMap<>();
        this.estado = EstadoPersona.INACTIVO;
    }

    public void agregarTelefono(String nombre, String telefono) {
        telefonos.put(nombre, telefono);
    }
}
