package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class HorarioConverter implements Converter<Horario> {

    @Autowired
    AdminTeatroServicio adminTeatroServicio;

    @Override
    public Horario getAsObject(FacesContext context, UIComponent component, String value) {
        Horario horario;

        try {
            horario = adminTeatroServicio.obtenerHorario( Integer.valueOf(value) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return horario;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Horario value) {
        if(value != null) {
            return "" + value.getId();
        }

        return "";
    }
}
