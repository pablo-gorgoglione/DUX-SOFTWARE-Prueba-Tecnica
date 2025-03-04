package com.base_api.services;

import com.base_api.dto.EquipoDTO;
import com.base_api.model.Equipo;
import com.base_api.repositories.EquipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public Equipo findById(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado"));
    }

    public List<Equipo> searchByName(String search) {
        return equipoRepository.searchByName(search);
    }

    public Equipo create(EquipoDTO createEquipoDTO) {
        Equipo equipo = Equipo.of(createEquipoDTO);
        return equipoRepository.save(equipo);
    }

    public Equipo update(EquipoDTO updateEquipoDTO, Long id) {
        Equipo equipo = findById(id);
        equipo.setNombre(updateEquipoDTO.getNombre());
        equipo.setLiga(updateEquipoDTO.getLiga());
        equipo.setPais(updateEquipoDTO.getPais());
        return equipoRepository.save(equipo);
    }

    public void delete(Long id) {
        Equipo equipo = findById(id);
        equipoRepository.delete(equipo);
    }
}
