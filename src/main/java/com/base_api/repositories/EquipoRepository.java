package com.base_api.repositories;

import com.base_api.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    @Query("SELECT e FROM Equipo e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Equipo> searchByName(String search);
}
