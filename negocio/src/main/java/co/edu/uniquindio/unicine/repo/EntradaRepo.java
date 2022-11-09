package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepo extends JpaRepository<Entrada, Integer> {

    @Query("select c.entradas from Compra c where c.funcion.id = :idFuncion")
    List<Entrada> obtenerEntradasCompradasFuncion(Integer idFuncion);
}
