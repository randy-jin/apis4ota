package com.westar.api.service;

import com.westar.api.domain.Recipe;
import com.westar.api.service.criteria.RecipeCriteria;
import com.westar.api.service.dto.RecipeDTO;
import com.westar.api.service.criteria.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Interface for managing {@link Recipe} with {@link Criteria}.
 */
public interface RecipeQBService {

    /**
     * Find recipes by criteria, all if criteria is null
     *
     * @param criteria the criteria to query
     * @param pageable pageable query params
     * @return the entity paginated.
     */
    @Transactional(readOnly = true)
    Page<RecipeDTO> findByCriteria(RecipeCriteria criteria, Pageable pageable);
}
