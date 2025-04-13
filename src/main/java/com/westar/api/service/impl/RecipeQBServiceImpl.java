package com.westar.api.service.impl;

import com.westar.api.domain.Ingredient_;
import com.westar.api.domain.Recipe;
import com.westar.api.domain.Recipe_;
import com.westar.api.repository.RecipeRepository;
import com.westar.api.service.RecipeQBService;
import com.westar.api.service.criteria.QueryBuilderService;
import com.westar.api.service.criteria.RecipeCriteria;
import com.westar.api.service.dto.RecipeDTO;
import com.westar.api.service.mapper.RecipeMapper;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RecipeQBServiceImpl extends QueryBuilderService<Recipe> implements RecipeQBService {

    private final Logger log = LoggerFactory.getLogger(RecipeQBService.class);

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    public RecipeQBServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RecipeDTO> findByCriteria(RecipeCriteria criteria, Pageable pageable) {
        log.debug("Request to find Recipes by criteria: " + criteria);
        Specification<Recipe> specification = buildSpecification(criteria);
        Page<Recipe> result = getPaginatedRecipes(specification, pageable);
        return result.map(recipeMapper::toDto);
    }

    /**
     * Function to convert {@link RecipeCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    private Specification<Recipe> buildSpecification(RecipeCriteria criteria) {
        Specification<Recipe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildFilterSpecification(criteria.getId(), Recipe_.id));
            }
            if (criteria.getCategory() != null) {
                specification = specification.and(buildFilterSpecification(criteria.getCategory(), Recipe_.category));
            }
            if (criteria.getServings() != null) {
                specification = specification.and(buildFilterSpecification(criteria.getServings(), Recipe_.servings));
            }
            if (criteria.getInstructions() != null) {
                specification = specification.and(buildFilterSpecification(criteria.getInstructions(), Recipe_.instructions));
            }
            if (criteria.getIngredients() != null) {
                specification = specification.and(
                        buildSpecification(
                                criteria.getIngredients(),
                                root -> root.join(Recipe_.ingredients, JoinType.LEFT).get(Ingredient_.name)
                        )
                );
            }
        }
        return specification;
    }

    private Page<Recipe> getPaginatedRecipes(Specification<Recipe> specification, Pageable pageable) {
        Page<Recipe> recipePage = recipeRepository.findAll(specification, pageable);
        List<Long> recipeIds = recipePage.getContent().stream().map(Recipe::getId).collect(Collectors.toList());
        List<Recipe> recipesWithIngredients = recipeRepository.findAllWithIngredientsByIds(recipeIds);

        return new PageImpl<>(recipesWithIngredients, pageable, recipePage.getTotalElements());
    }
}
