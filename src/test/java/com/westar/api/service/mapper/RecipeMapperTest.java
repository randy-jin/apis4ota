package com.westar.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class RecipeMapperTest {

    private RecipeMapper recipeMapper;

    @BeforeEach
    public void setUp() {
        recipeMapper = new RecipeMapperImpl();
    }
}
