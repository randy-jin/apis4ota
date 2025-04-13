package com.westar.api.service.dto;


import com.westar.api.domain.Ingredient;
import jakarta.validation.constraints.NotBlank;

/**
 * A DTO for the {@link Ingredient} entity.
 */
public record IngredientDTO(Long id,
                            @NotBlank(message = "Name is required") String name) {
}
