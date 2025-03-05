package com.base_api.services;

import com.base_api.dto.EquipoDTO;
import com.base_api.model.Equipo;
import com.base_api.repositories.EquipoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class EquipoServiceTest {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private EquipoRepository equipoRepository;

    @Test
    void testFindAll() {
        String nombreEquipo1 = "Equipo Prueba 1";
        String nombreEquipo2 = "Equipo Prueba 2";
        List<Equipo> equipos = List.of(new Equipo(nombreEquipo1, "Liga 1", "Argentina"),
                new Equipo(nombreEquipo2, "Liga 2", "España"));

        equipoRepository.saveAll(equipos);

        List<Equipo> result = equipoService.findAll();

        Optional<Equipo> equipo1 = result.stream().filter(e -> nombreEquipo1.equals(e.getNombre())).findFirst();

        Optional<Equipo> equipo2 = result.stream().filter(e -> nombreEquipo2.equals(e.getNombre())).findFirst();

        Assertions.assertTrue(equipo1.isPresent());
        Assertions.assertTrue(equipo2.isPresent());
    }

    @Test
    void testFindById_Found() {
        Equipo result = equipoService.findById(1L);

        Assertions.assertNotNull(result);
    }

    @Test
    void testFindById_NotFound() {
        EquipoDTO dto = new EquipoDTO("Equipo para borrar", "Serie A", "Italia");

        Equipo equipoToDelete = equipoService.create(dto);
        equipoService.delete(equipoToDelete.getId());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                () -> equipoService.findById(equipoToDelete.getId()));
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode().value());
    }

    @Test
    void testSearchByName() {
        EquipoDTO dto = new EquipoDTO("Juventus FC", "Serie A", "Italia");

        equipoService.create(dto);
        List<Equipo> result = equipoService.searchByName("juventus");

        Assertions.assertFalse(result.isEmpty());
        boolean containsJuventus = result.stream().anyMatch(e -> e.getNombre().toLowerCase().contains("juventus"));

        Assertions.assertTrue(containsJuventus);

    }


    @Test
    void testCreate() {
        EquipoDTO dto = new EquipoDTO("Equipo C", "Liga 3", "Brasil");

        Equipo result = equipoService.create(dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Equipo C", result.getNombre());
    }

    @Test
    void testUpdate() {
        EquipoDTO dto = new EquipoDTO("Equipo 123", "Liga 3", "Brasil");

        Equipo result = equipoService.create(dto);

        EquipoDTO updateDto = new EquipoDTO("Equipo Updated", "Liga 1", "Chile");

        Equipo updatedEquipo = equipoService.update(updateDto, result.getId());

        Assertions.assertEquals("Equipo Updated", updatedEquipo.getNombre());
        Assertions.assertEquals("Chile", updatedEquipo.getPais());
    }

    @Test
    void testDelete() {
        Equipo equipo = equipoService.create(new EquipoDTO("Equipo A", "Liga 1", "Argentina"));

        Assertions.assertDoesNotThrow(() -> equipoService.delete(equipo.getId()));

        Optional<Equipo> foundEquipo = equipoRepository.findById(equipo.getId());
        Assertions.assertEquals(Optional.empty(), foundEquipo);

    }
}
