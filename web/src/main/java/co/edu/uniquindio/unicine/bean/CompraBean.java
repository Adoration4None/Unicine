package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@ViewScoped
public class CompraBean implements Serializable {
    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Value("#{param['city']}")
    private String idCiudad;

    @Value("#{param['func']}")
    private String idFuncion;

    @Value(value = "#{seguridadBean.cliente}")
    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private Funcion funcion;

    @Getter @Setter
    private Compra compra;

    @Getter @Setter
    private List<Confiteria> confiteria;

    @Getter @Setter
    private List< List<Entrada> > matriz;

    @Getter @Setter
    private List<Entrada> seleccionadas;

    private Integer filas, columnas;

    @Getter @Setter
    private List<CompraConfiteria> comprasConfiteria;

    @Getter @Setter
    private int unidadesCompradas;

    @Getter @Setter
    private Float valorTotalEntradas;

    @Getter @Setter
    private Float valorTotalConfiteria;

    @Getter @Setter
    private Float valorTotal;

    @PostConstruct
    public void init() {
        seleccionadas = new ArrayList<>();
        matriz = new ArrayList<>();
        comprasConfiteria = new ArrayList<>();
        confiteria = administradorServicio.listarConfiteria();
        unidadesCompradas = 0;
        compra = new Compra();

        if(idFuncion != null && !idFuncion.isEmpty()) {
            try {
                funcion = adminTeatroServicio.obtenerFuncion( Integer.valueOf(idFuncion) );
                filas = funcion.getSala().getFilas();
                columnas = funcion.getSala().getColumnas();

                for(int i = 0; i < filas; i++){
                    List<Entrada> fila = new ArrayList<>();

                    for(int j = 0; j < columnas; j++){
                        fila.add( Entrada.builder().filaAsiento(i).columnaAsiento(j).build() );
                    }
                    matriz.add(fila);
                }
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    public void comprar(){
        compra.setFuncion(funcion);
        compra.setCliente(cliente);

        compra.setEntradas(seleccionadas);

    }

    public void guadarSilla(int fila, int columna){
        boolean repetida = false;

        //Validar que la entrada con la misma fila y columna no exista en la lista
        if(seleccionadas.size() > 0) {

            // Con foreach no sirve (???)
            for(int i = 0; i < seleccionadas.size(); i++) {
                if( seleccionadas.get(i).getFilaAsiento() == fila && seleccionadas.get(i).getColumnaAsiento() == columna ) {
                    seleccionadas.remove(i);
                    repetida = true;
                }
            }
        }

        if(!repetida) seleccionadas.add( Entrada.builder().filaAsiento(fila).columnaAsiento(columna).build() );

        System.out.println(seleccionadas);
    }

    public void agregarComestible(Confiteria comestible) {
        unidadesCompradas += 1;
        CompraConfiteria compraConfiteria = new CompraConfiteria(comestible.getPrecio(), unidadesCompradas, compra, comestible);
        comprasConfiteria.add(compraConfiteria);
    }

    public void quitarComestible(Confiteria comestible) {
        if(unidadesCompradas != 0) {
            unidadesCompradas -= 1;

            CompraConfiteria compraConfiteria = new CompraConfiteria(comestible.getPrecio(), unidadesCompradas, compra, comestible);

            for(int i = 0; i < comprasConfiteria.size(); i++) {
                if( comprasConfiteria.get(i).getComestible().equals(comestible)  ) {
                    comprasConfiteria.remove(i);
                }
            }
        }

    }

    public void calcularTotalEntradas() {
        compra.setEntradas(seleccionadas);

        valorTotalEntradas = compra.obtenerTotalEntradas();
    }

    public void calcularTotalConfiteria() {
        compra.setComprasConfiteria(comprasConfiteria);

        valorTotalConfiteria = compra.obtenerTotalConfiteria();
    }

    public void calcularTotalCompra() {
        try {
            compra.setEntradas(seleccionadas);
            setComprasConfiteria(comprasConfiteria);
            valorTotal = compra.getValorTotal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
    }
}
