package com.westar.api.service.impl;

import com.westar.api.domain.Recipe;
import com.westar.api.repository.RecipeRepository;
import com.westar.api.service.dto.RecipeDTO;
import com.westar.api.service.mapper.RecipeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMapper recipeMapper;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        RecipeDTO recipeDTO = new RecipeDTO(null, null, null, null, null);
        Recipe recipe = new Recipe();
        when(recipeMapper.toEntity(recipeDTO)).thenReturn(recipe);
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDTO);

        RecipeDTO result = recipeService.save(recipeDTO);

        assertEquals(recipeDTO, result);
        verify(recipeRepository, times(1)).save(recipe);
        verify(recipeMapper, times(1)).toDto(recipe);
    }

    @Test
    public void testUpdate() {
        RecipeDTO recipeDTO = new RecipeDTO(null, null, null, null, null);
        Recipe recipe = new Recipe();
        when(recipeMapper.toEntity(recipeDTO)).thenReturn(recipe);
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDTO);

        RecipeDTO result = recipeService.update(recipeDTO);

        assertEquals(recipeDTO, result);
        verify(recipeRepository, times(1)).save(recipe);
        verify(recipeMapper, times(1)).toDto(recipe);
    }

    @Test
    public void testFindOne() {
        Long id = 1L;
        Recipe recipe = new Recipe();
        RecipeDTO recipeDTO = new RecipeDTO(null, null, null, null, null);
        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDTO);

        Optional<RecipeDTO> result = recipeService.findOne(id);

        assertEquals(recipeDTO, result.orElse(null));
        verify(recipeRepository, times(1)).findById(id);
        verify(recipeMapper, times(1)).toDto(recipe);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        recipeService.delete(id);
        verify(recipeRepository, times(1)).deleteById(id);
    }
}
