package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Cliente extends Persona implements Serializable {
    // Atributos extra
    private LocalDate fechaNacimiento;

    @Length(max = 10)
    @Column(length = 10)
    private String telefonoCelular;

    @Length(max = 10)
    @Column(length = 10)
    private String telefonoFijo;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @MapKeyColumn(name = "publicIdImagen")
    @Column(name = "urlImagen")
    @CollectionTable(name = "cliente_imagen")
    private Map<String, String> imagenPerfil = new HashMap<>();

    @ElementCollection
    @Column(name = "anio")
    private List<Integer> aniosCelebrados = new ArrayList<>();

    // Relaciones ---------------------------------------------------------------------------------------
    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<CuponCliente> cuponesCliente = new ArrayList<>();

    // Constructor --------------------------------------------------------------------------------------
    @Builder
    public Cliente(String cedula, String nombreCompleto, String email, String contrasena) {
        super(cedula, nombreCompleto, email, contrasena);
    }

    // Otros metodos -----------------------------------------------------------------------------------
    public void agregarAnioCelebrado(Integer anio) {
        aniosCelebrados.add(anio);
    }

    public String getFoto() {
        if(imagenPerfil == null || imagenPerfil.isEmpty()) {
            return "https://res.cloudinary.com/dheuspgiq/image/upload/v1668978850/unicine/person_p5oatx.png";
        }

        String primera = imagenPerfil.keySet().toArray()[0].toString();
        return imagenPerfil.get(primera);
    }

}
