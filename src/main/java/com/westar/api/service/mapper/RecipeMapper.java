package com.westar.api.service.mapper;

import com.westar.api.domain.Ingredient;
import com.westar.api.domain.Recipe;
import com.westar.api.service.dto.IngredientDTO;
import com.westar.api.service.dto.RecipeDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link Recipe} and its DTO {@link RecipeDTO}.
 */
@Mapper(componentModel = "spring")
public interface RecipeMapper extends EntityMapper<RecipeDTO, Recipe> {
    @Mapping(target = "ingredients", source = "ingredients", qualifiedByName = "ingredientNameSet")
    RecipeDTO toDto(Recipe s);

    @Mapping(target = "removeIngredient", ignore = true)
    Recipe toEntity(RecipeDTO recipeDTO);

    @Named("ingredientName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    IngredientDTO toDtoIngredientName(Ingredient ingredient);

    @Named("ingredientNameSet")
    default Set<IngredientDTO> toDtoIngredientNameSet(Set<Ingredient> ingredient) {
        return ingredient.stream().map(this::toDtoIngredientName).collect(Collectors.toSet());
    }
}
