package com.base_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipoDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String liga;

    @NotBlank
    private String pais;
}
