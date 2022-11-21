package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class GeneroConverter implements Converter<Genero> {
    @Autowired
    private AdministradorServicio administradorServicio;

    @Override
    public Genero getAsObject(FacesContext context, UIComponent component, String value) {
        Genero genero;

        try {
            genero = administradorServicio.obtenerGenero( Integer.valueOf(value) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return genero;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Genero value) {
        if(value != null) {
            return "" + value.getId();
        }

        return "";
    }
}
