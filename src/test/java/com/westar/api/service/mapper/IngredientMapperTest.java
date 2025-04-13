package com.westar.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class IngredientMapperTest {

    private IngredientMapper ingredientMapper;

    @BeforeEach
    public void setUp() {
        ingredientMapper = new IngredientMapperImpl();
    }
}
