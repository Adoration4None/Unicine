package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepo extends JpaRepository<Cupon, Integer> {

    //validar cupón
    @Query("select case when (count(cup) > 0) then true else false end from Cupon cup where cup.id = :id")
    boolean validarCupon(Integer id);

}
