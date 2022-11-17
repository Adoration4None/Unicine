package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CompraBean implements Serializable {
    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Value("#{param['city']}")
    private String idCiudad;

    @Value("#{param['func']}")
    private String idFuncion;

    @Getter @Setter
    private List<Integer> listaAsientosSala;

    @Getter @Setter
    private List<Integer> asientosEscogidos;

    @Getter @Setter
    private Funcion funcion;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private Compra compra;

    private Integer filas, columnas;

    @Getter @Setter
    private List< List<Entrada> > matriz;

    @Getter @Setter
    private List<Entrada> seleccionadas;

    @PostConstruct
    public void init() {
        if(idFuncion != null && !idFuncion.isEmpty()) {
            try {
                funcion = adminTeatroServicio.obtenerFuncion( Integer.valueOf(idFuncion) );
                listaAsientosSala = llenarListaAsientos( funcion.getSala().getCantidadSillas() );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        seleccionadas = new ArrayList<>();
        matriz = new ArrayList<>();
        filas = 10;
        columnas = 5;

        for( int i = 0; i<filas ; i++ ){
            List<Entrada> fila = new ArrayList<>();
            for( int j = 0; j<columnas ; j++ ){
                fila.add( Entrada.builder().filaAsiento(i).columnaAsiento(j).build() );
            }
            matriz.add(fila);
        }


    }

    public void comprar(){

        Compra compra = new Compra();
        compra.setEntradas(seleccionadas);

    }

    private List<Integer> llenarListaAsientos(Integer cantidadSillas) {
        List<Integer> lista = new ArrayList<>();

        for(int i = 1; i <= cantidadSillas; i++) {
            lista.add(i);
        }

        return lista;
    }

    public void guadarSilla(int fila, int columna){
        //Validar que la entrada con la misma fila y columna no exista en la lista

        seleccionadas.add( Entrada.builder().filaAsiento(fila).columnaAsiento(columna).build() );

        System.out.println(seleccionadas);

    }
}
