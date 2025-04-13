package com.westar.api;

import com.westar.api.repository.IngredientRepository;
import com.westar.api.repository.RecipeRepository;
import com.westar.api.service.IngredientService;
import com.westar.api.service.RecipeQBService;
import com.westar.api.service.RecipeService;
import com.westar.api.service.mapper.IngredientMapper;
import com.westar.api.service.mapper.RecipeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private RecipeQBService recipeQBService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private IngredientMapper ingredientMapper;


    @Test
    void contextLoads() {
        assertNotNull(recipeQBService);
        assertNotNull(recipeService);
        assertNotNull(ingredientService);
        assertNotNull(recipeRepository);
        assertNotNull(ingredientRepository);
        assertNotNull(recipeMapper);
        assertNotNull(ingredientMapper);
    }

}
