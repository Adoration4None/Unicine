package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {
    Horario findByFechaAndHora(LocalDate fecha, LocalTime hora);

    @Query("select h from  Horario h where h.funciones.size = 0")
    List<Horario> obtenerHorariosDisponibles();
}
