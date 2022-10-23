package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra implements Serializable {
    // Atributos -----------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    private Float valorTotal;

    // Relaciones ------------------------------------------------------
    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<CompraConfiteria> comprasConfiteria;

    @ManyToOne
    @ToString.Exclude
    private Cupon cupon;

    @ManyToOne
    private Funcion funcion;

    @OneToMany(mappedBy = "compra")
    @NonNull
    @ToString.Exclude
    private List<Entrada> entradas;

    // Constructor --------------------------------------------------------------------------------------
   @Builder
    public Compra(LocalDateTime fechaCompra, MetodoPago metodoPago, Cliente cliente, Funcion funcion, List<Entrada> entradas) {
        this.fechaCompra = fechaCompra;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.funcion = funcion;
        this.entradas = entradas;

       System.out.println(id);
    }

    public Float calcularValorTotal() throws Exception {
        float total = 0f;

        if(this.entradas == null || this.funcion == null)
            throw new Exception("No se puede calcular el valor de la compra");

        total += obtenerTotalEntradas();

        if(this.comprasConfiteria != null) total += obtenerTotalConfiteria();
        if(this.cupon != null) total -= (total * this.cupon.getPorcentajeDescuento());

        this.valorTotal = total;
        return this.valorTotal;
    }

    public float obtenerTotalConfiteria() {
        float total = 0f;
        for (CompraConfiteria c : this.comprasConfiteria) total += c.getPrecio();
        return total;
    }

    public float obtenerTotalEntradas() {
        return this.funcion.getPrecio() * entradas.size();
    }
}
