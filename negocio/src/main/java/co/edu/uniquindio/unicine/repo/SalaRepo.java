package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepo extends JpaRepository<Sala, Integer> {

    @Query("select f from Sala s, in (s.funciones) f where s.id = :idSala ")
    List<Funcion> obtenerFuncionesSala(Integer idSala);

    /**
     * Verifica si es posible asignar una cantidad de asientos ingresada a una compra
     * @param cantidadAsientos numero de entradas a comprar
     * @return true si la cantidad de asientos no excede la cantidad de sillas de la sala, false en caso contrario
     */
    @Query("select case when count(c.entradas) + :cantidadAsientos > c.funcion.sala.cantidadSillas then false else true end from Compra c")
    boolean verificarAsientosDisponibles(int cantidadAsientos);

    Sala findByCantidadSillasAndTipoAndTeatro(Integer cantidadSillas, TipoSala tipo, Teatro teatro);

    @Query("select f from Sala s, in (s.funciones) f where s.id = :idSala and f.pelicula.estado = :estadoPelicula")
    List<Funcion> obtenerFuncionesSalaEstado(Integer idSala, EstadoPelicula estadoPelicula);
}
