package com.base_api.model;

import com.base_api.dto.EquipoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String liga;
    private String pais;

    public static Equipo of(EquipoDTO dto){
        Equipo equipo = new Equipo();
        equipo.setNombre(dto.getNombre());
        equipo.setLiga(dto.getLiga());
        equipo.setPais(dto.getPais());
        return equipo;
    }
}
