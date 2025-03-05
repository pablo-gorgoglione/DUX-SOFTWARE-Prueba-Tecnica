package com.base_api.controllers;

import com.base_api.dto.EquipoDTO;
import com.base_api.model.Equipo;
import com.base_api.services.EquipoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> findAll() {
        return ResponseEntity.ok(equipoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(equipoService.findById(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> searchByName(@RequestParam(name = "nombre") String nombre) {
        return ResponseEntity.ok(equipoService.searchByName(nombre));
    }

    @PostMapping
    public ResponseEntity<Equipo> create(@Valid @RequestBody EquipoDTO equipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.create(equipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> update(@RequestBody EquipoDTO equipo, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(equipoService.update(equipo, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
