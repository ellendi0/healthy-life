package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.enums.RecipeAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByIdAndRecipeAccess(Long recipeId, RecipeAccess recipeAccess);
    Boolean existsByIdAndRecipeAccess(Long recipeId, RecipeAccess recipeAccess);
    Optional<Recipe> findByPersonalInfo_IdAndId(Long personalInfoId, Long recipeId);
    Boolean existsByPersonalInfo_IdAndId(Long personalInfoId, Long recipeId);
    List<Recipe> findAllByPersonalInfo_Id(Long personalInfoId);
    Boolean existsByPersonalInfo_Id(Long personalInfoId);
    Optional<Recipe> findAllByRecipeAccess(RecipeAccess recipeAccess);
    Recipe deleteByPersonalInfo_IdAndId(Long personalInfoId, Long recipeId);
    Recipe deleteAllByPersonalInfo_Id(Long personalInfoId);
}
