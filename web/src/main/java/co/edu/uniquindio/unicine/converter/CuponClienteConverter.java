package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class CuponClienteConverter implements Converter<CuponCliente> {
    @Autowired
    private ClienteServicio clienteServicio;

    @Override
    public CuponCliente getAsObject(FacesContext context, UIComponent component, String value) {
        CuponCliente cuponCliente;

        try {
            cuponCliente = clienteServicio.obtenerCuponCliente( Integer.valueOf(value) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return cuponCliente;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, CuponCliente value) {
        if(value != null) {
            return "" + value.getId();
        }

        return "";
    }
}
