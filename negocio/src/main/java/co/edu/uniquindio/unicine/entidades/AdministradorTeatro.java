package co.edu.uniquindio.unicine.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class AdministradorTeatro extends Persona implements Serializable {
    // Atributo extra
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @MapKeyColumn(name = "publicIdImagen")
    @Column(name = "urlImagen")
    @CollectionTable(name = "administrador_imagen")
    private Map<String, String> imagenPerfil;

    // Relaciones -----------------------------------------------------------------------------------------
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    @JsonIgnore
    private List<Teatro> teatros = new ArrayList<>();

    @OneToOne
    private Ciudad ciudad;

    // Constructor ----------------------------------------------------------------------------------------
    public AdministradorTeatro(String cedula, String nombreCompleto, String email, String contrasena) {
        super(cedula, nombreCompleto, email, contrasena);
    }

    public String getFoto() {
        if(imagenPerfil == null || imagenPerfil.isEmpty()) {
            return "https://res.cloudinary.com/dheuspgiq/image/upload/v1668978850/unicine/person_p5oatx.png";
        }

        String primera = imagenPerfil.keySet().toArray()[0].toString();
        return imagenPerfil.get(primera);
    }
}
