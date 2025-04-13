package com.westar.api.service.mapper;

import com.westar.api.domain.Ingredient;
import com.westar.api.service.dto.IngredientDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Ingredient} and its DTO {@link IngredientDTO}.
 */
@Mapper(componentModel = "spring")
public interface IngredientMapper extends EntityMapper<IngredientDTO, Ingredient> {
}
