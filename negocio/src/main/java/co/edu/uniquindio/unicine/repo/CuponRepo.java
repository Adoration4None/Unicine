package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepo extends JpaRepository<Cupon, Integer> {

    @Query("select case when (count(cup) > 0) then true else false end from CuponCliente cup where cup.cupon.id = :id and cup.estado = 'DISPONIBLE'")
    boolean validarCuponCliente(Integer id);

    Cupon findByNombre(String nombre);
}
