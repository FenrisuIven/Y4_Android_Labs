package com.example.lb1.repositories.recipe

import com.example.lb1.repositories.recipe.dto.CreateRecipeDto
import com.example.lb1.repositories.recipe.dto.RecipeDto
import com.example.lb1.repositories.recipe.dto.UpdateRecipeDto
import com.example.lb1.repositories.recipe.types.FindOneRecipePayload

interface RecipeBase {
  suspend fun getAll(): List<RecipeDto>
  suspend fun getOne(payload: FindOneRecipePayload): RecipeDto
  suspend fun create(dto: CreateRecipeDto): Long
  suspend fun update(id: Int, dto: UpdateRecipeDto)
  suspend fun delete(id: Int)
}