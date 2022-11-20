package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CompraBean implements Serializable {
    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['func']}")
    private String idFuncion;

    @Value("#{seguridadBean.ciudad.id}")
    private String idCiudad;

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

    @Getter @Setter
    private List<Entrada> entradasCompradas;

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

    @Getter @Setter
    private List<MetodoPago> metodosPago = List.of( MetodoPago.values() );

    @Getter @Setter
    private MetodoPago metodoPago;

    @Getter @Setter
    private List<CuponCliente> cuponesCliente;

    @Getter @Setter
    private CuponCliente cupon;

    @PostConstruct
    public void init() {
        seleccionadas = new ArrayList<>();
        matriz = new ArrayList<>();
        comprasConfiteria = new ArrayList<>();
        compra = new Compra();
        confiteria = administradorServicio.listarConfiteria();
        cupon = new CuponCliente();

        unidadesCompradas = 0;
        valorTotalConfiteria = 0.0f;
        valorTotalEntradas = 0.0f;
        valorTotal = 0.0f;

        compra.setCliente(cliente);

        if(idFuncion != null && !idFuncion.isEmpty()) {
            try {
                cuponesCliente = clienteServicio.obtenerCuponesClienteEstado(cliente, EstadoCupon.DISPONIBLE);
                funcion = adminTeatroServicio.obtenerFuncion( Integer.valueOf(idFuncion) );
                entradasCompradas = clienteServicio.obtenerEntradasCompradas(funcion);
                System.out.println(entradasCompradas.toString());

                compra.setFuncion(funcion);
                int filas = funcion.getSala().getFilas();
                int columnas = funcion.getSala().getColumnas();

                for(int i = 1; i <= filas; i++){
                    List<Entrada> fila = new ArrayList<>();

                    for(int j = 1; j <= columnas; j++){
                        fila.add( Entrada.builder().filaAsiento(i).columnaAsiento(j).precioBase(8000f).build() );
                    }
                    matriz.add(fila);
                }
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    public void guadarSilla(int fila, int columna){
        boolean repetida = false;

        if(seleccionadas.size() > 0) {

            // Con foreach no sirve (???)
            for(int i = 0; i < seleccionadas.size(); i++) {
                if( seleccionadas.get(i).getFilaAsiento() == fila && seleccionadas.get(i).getColumnaAsiento() == columna ) {
                    seleccionadas.remove(i);
                    repetida = true;
                }
            }
        }

        for(int i = 0; i < entradasCompradas.size(); i++) {
            if( entradasCompradas.get(i).getFilaAsiento() == fila && entradasCompradas.get(i).getColumnaAsiento() == columna ) {
                mostrarError( new Exception("La silla " + entradasCompradas.get(i).getFilaAsiento() + "-" + entradasCompradas.get(i).getColumnaAsiento() + " ya esta ocupada") );
                repetida = true;
            }
        }

        if(!repetida) {
            Entrada entrada = Entrada.builder().filaAsiento(fila).columnaAsiento(columna).precioBase(8000f).build();
            entrada.setCompra(compra);
            entrada.setSala( funcion.getSala() );
            seleccionadas.add(entrada);
        }

        calcularTotalEntradas();
        calcularTotalCompra();
    }

    public void agregarComestible(Confiteria comestible) {
        obtenerUnidadesCompradas(comestible);

        if(unidadesCompradas == 0) {
            CompraConfiteria compraConfiteria = new CompraConfiteria(comestible.getPrecio(), 1, compra, comestible);
            comprasConfiteria.add(compraConfiteria);
            unidadesCompradas++;
        }
        else {
            for(int i = 0; i < comprasConfiteria.size(); i++) {
                if( comprasConfiteria.get(i).getComestible().equals(comestible)  ) {
                    unidadesCompradas = comprasConfiteria.get(i).actualizarUnidadesCompradas('+');
                }
            }
        }

        calcularTotalConfiteria();
        calcularTotalCompra();
    }

    public void quitarComestible(Confiteria comestible) {
        obtenerUnidadesCompradas(comestible);

        if(unidadesCompradas != 0) {

            for(int i = 0; i < comprasConfiteria.size(); i++) {
                if( comprasConfiteria.get(i).getComestible().equals(comestible)  ) {

                    if( comprasConfiteria.get(i).getUnidadesCompradas() == 1 ) {
                        comprasConfiteria.remove(i);
                        unidadesCompradas = 0;
                    }
                    else {
                        unidadesCompradas = comprasConfiteria.get(i).actualizarUnidadesCompradas('-');
                    }
                }
            }
        }

        calcularTotalConfiteria();
        calcularTotalCompra();
    }

    private void obtenerUnidadesCompradas(Confiteria comestible) {
        unidadesCompradas = 0;

        for (CompraConfiteria compraConfiteria : comprasConfiteria) {
            if (compraConfiteria.getComestible().equals(comestible)) {
                unidadesCompradas = compraConfiteria.getUnidadesCompradas();
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
            compra.setComprasConfiteria(comprasConfiteria);
            compra.setFuncion(funcion);

            valorTotal = compra.calcularValorTotal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void seleccionarMetodoPago() {
        compra.setMetodoPago(metodoPago);
    }

    public void asignarCupon() {
        compra.setCuponCliente(cupon);
    }

    public String finalizarCompra(){
        if(seleccionadas.size() == 0) mostrarError( new Exception("Debes comprar al menos una entrada") );
        if(metodoPago == null) mostrarError( new Exception("Debes seleccionar un metodo de pago") );
        if(cupon != null && cupon.getEstado() != EstadoCupon.DISPONIBLE )
            mostrarError( new Exception("El cupon que seleccionaste no esta disponible") );

        compra.setEntradas(seleccionadas);
        compra.setComprasConfiteria(comprasConfiteria);
        compra.setMetodoPago(metodoPago);

        if(cupon != null) compra.setCuponCliente(cupon);

        try {
            clienteServicio.registrarCompra(compra);
            return "/cliente/detalle-compra?faces-redirect=true&amp;b=" + compra.getId();
        } catch (Exception e) {
            mostrarError(e);
        }

        return "";
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        PrimeFaces.current().dialog().showMessageDynamic(fm);
    }
}
