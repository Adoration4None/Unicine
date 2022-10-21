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

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoPago;

    private Float valorTotal;

    // Relaciones ------------------------------------------------------
    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "compra")
    private List<CompraConfiteria> comprasConfiteria;

    @OneToOne
    private Cupon cupon;

    @ManyToOne
    private Funcion funcion;

    @OneToMany(mappedBy = "compra")
    @NonNull
    private List<Entrada> entradas;

    // Constructor --------------------------------------------------------------------------------------
   @Builder
    public Compra(LocalDateTime fechaCompra, MetodoPago metodoPago, Cliente cliente, Funcion funcion, List<Entrada> entradas) {
        this.fechaCompra = fechaCompra;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.funcion = funcion;
        this.entradas = entradas;
    }

    public Float calcularValorTotal() throws Exception {
        float total = 0f;

        if(this.entradas == null || this.funcion == null)
            throw new Exception("No se puede calcular el valor de la compra");

        total += this.funcion.getPrecio() * entradas.size();

        if(this.comprasConfiteria != null) total += obtenerTotalConfiteria();
        if(this.cupon != null) total -= this.cupon.getValorDescuento();

        this.valorTotal = total;
        return this.valorTotal;
    }

    public float obtenerTotalConfiteria() {
        float total = 0f;
        for (CompraConfiteria c : this.comprasConfiteria) total += c.getPrecio();
        return total;
    }

}
