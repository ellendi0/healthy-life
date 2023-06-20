package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.mapper.FoodMapper;
import com.webapp.app_rest_api.mapper.RecipeMapper;
import com.webapp.app_rest_api.model.*;
import com.webapp.app_rest_api.repository.FoodRepository;
import com.webapp.app_rest_api.repository.FoodToRecipeRepository;
import com.webapp.app_rest_api.repository.RecipeRepository;
import com.webapp.app_rest_api.service.IFoodService;
import com.webapp.app_rest_api.service.IRecipeService;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RecipeService implements IRecipeService {

    private RecipeRepository recipeRepository;
    private IFoodService iFoodService;
    private RecipeMapper recipeMapper;
    private FoodMapper foodMapper;
    private FoodToRecipeRepository foodToRecipeRepository;
    private FoodRepository foodRepository;

    public RecipeService(RecipeRepository recipeRepository, IFoodService iFoodService, RecipeMapper recipeMapper, FoodMapper foodMapper, FoodToRecipeRepository foodToRecipeRepository, FoodRepository foodRepository) {
        this.recipeRepository = recipeRepository;
        this.iFoodService = iFoodService;
        this.recipeMapper = recipeMapper;
        this.foodMapper = foodMapper;
        this.foodToRecipeRepository = foodToRecipeRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public RecipeDto getRecipeById(long id) {
        return recipeMapper.mapToDto(recipeRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Recipe", "id", String.valueOf(id))));
    }

    @Override
    public List<RecipeDto> getAllRecipe() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::mapToDto)
                .toList();
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public RecipeDto addFoodToRecipe(long recipeId, long foodId, double weight) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(recipeId)));

        RecipeDto recipeDto = recipeMapper.mapToDto(recipe);

        FoodToRecipe foodToRecipe = recipe.getFood().stream()
                .filter(f -> f.getFood().getId().equals(foodId))
                .findFirst()
                .orElse(null);

        if(Objects.isNull(foodToRecipe)){
            Food food = foodRepository.findById(foodId).orElseThrow(()
                    -> new ResourceNotFoundException("Food", "id", String.valueOf(foodId)));

            FoodToRecipe foodToRecipeNew = new FoodToRecipe(food, recipe, weight);

            recipe.getFood().add(foodToRecipeNew);
            food.getRecipe().add(foodToRecipeNew);
            foodToRecipeRepository.save(foodToRecipeNew);

            recipeDto.getFood().add(iFoodService.getFoodWithGivenWeight(foodId, weight));
        }else{
            foodToRecipe.setWeight(foodToRecipe.getWeight() + weight);
            recipeDto.getFood().removeIf(f -> f.getId().equals(foodId));
            recipeDto.getFood().add(iFoodService.getFoodWithGivenWeight(foodId, foodToRecipe.getWeight()));
        }
        recipeRepository.save(recipe);
        countNutritiousFromFoodList(recipeDto);
        return recipeDto;
    }

    @Override
    public RecipeDto updateRecipe(long recipeId, RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.mapToEntity(getRecipeById(recipeId));
        recipe.setName(recipeDto.getName());
        return recipeMapper.mapToDto(recipeRepository.save(recipe));
    }

    @Override
    public RecipeDto updateFoodInRecipe(long recipeId, long foodId, double weight) {
        RecipeDto recipeDto = recipeMapper.mapToDto(recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(recipeId))));

        recipeDto.getFood().removeIf(f -> f.getId().equals(foodId));
        recipeDto.getFood().add(iFoodService.getFoodWithGivenWeight(foodId, weight));
        countNutritiousFromFoodList(recipeDto);
        return recipeDto;
    }

    @Override
    public void deleteRecipe(long id) {
        recipeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteFoodFromRecipe(long recipeId, long foodId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(recipeId)));

        Food food = recipe.getFood().stream().map(FoodToRecipe::getFood)
                        .filter(f -> f.getId().equals(foodId))
                        .findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException("Food in Recipe", "id", String.valueOf(foodId)));

        recipe.getFood().removeIf(f -> f.getFood().getId().equals(foodId));
        food.getRecipe().removeIf(r -> r.getRecipe().getId().equals(recipeId));
        foodToRecipeRepository.deleteByRecipeIdAndFoodId(recipeId, foodId);
        recipeRepository.save(recipe);

        countNutritiousFromFoodList(recipeMapper.mapToDto(recipe));
    }

    @Override
    public void countNutritiousFromFoodList(RecipeDto recipeDto) {
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;

        if(recipeDto.getFood() != null) {
            for (FoodDto foodDto : recipeDto.getFood()) {
                calories += foodDto.getNumberOfCalories();
                proteins += foodDto.getNumberOfProtein();
                fats += foodDto.getNumberOfFat();
                carbohydrates += foodDto.getNumberOfCarbohydrate();
                weight += foodDto.getWeight();
            }
        }

        recipeDto.setNumberOfCalories(calories);
        recipeDto.setNumberOfProtein(proteins);
        recipeDto.setNumberOfFat(fats);
        recipeDto.setNumberOfCarbohydrate(carbohydrates);
        recipeDto.setWeight(weight);
        recipeRepository.save(recipeMapper.mapToEntity(recipeDto));
    }

    @Override
    public RecipeDto getRecipeWithGivenWeight(long recipeId, double weight) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()
                -> new ResourceNotFoundException("Recipe", "id", String.valueOf(recipeId)));
        RecipeDto recipeDto = recipeMapper.mapToDto(recipe);
        recipeDto.setId(recipe.getId());
        recipeDto.setName(recipe.getName());
        recipeDto.setWeight(weight);
        recipeDto.setNumberOfCalories(DoubleRounder.round(recipeDto.getNumberOfCalories() * weight / 100, 3));
        recipeDto.setNumberOfFat(DoubleRounder.round(recipeDto.getNumberOfFat() * weight / 100, 3));
        recipeDto.setNumberOfCarbohydrate(DoubleRounder.round(recipeDto.getNumberOfCarbohydrate() * weight / 100, 3));
        recipeDto.setNumberOfProtein(DoubleRounder.round(recipeDto.getNumberOfProtein() * weight / 100, 3));
        recipeDto.setNumberOfSugar(DoubleRounder.round(recipeDto.getNumberOfSugar() * weight / 100, 3));
        recipeDto.setNumberOfFiber(DoubleRounder.round(recipeDto.getNumberOfFiber() * weight / 100, 3));
        return recipeDto;
    }
}
