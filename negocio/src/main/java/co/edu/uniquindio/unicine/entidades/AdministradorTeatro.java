package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AdministradorTeatro implements Serializable {
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

    @Column(length = 10)
    private String celular;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoAdministrador estado;

    private String imagenPerfil;
}
