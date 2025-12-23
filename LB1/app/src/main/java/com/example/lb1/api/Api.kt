package com.example.lb1.api

import com.example.lb1.repositories.category.dto.CategoryDto
import com.example.lb1.repositories.ingredient.dto.IngredientDto
import com.example.lb1.repositories.recipe.dto.RecipeDto
import retrofit2.Response
import retrofit2.http.GET

interface Api {
  @GET("/all-recipes")
  suspend fun getRecipes(): Response<List<RecipeDto>>

  @GET("/all-categories")
  suspend fun getCategories(): Response<List<CategoryDto>>

  @GET("/all-ingredients")
  suspend fun getIngredients(): Response<List<IngredientDto>>
}