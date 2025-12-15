package com.example.lb1.repositories.recipe

import com.example.lb1.dao.AppDao
import com.example.lb1.entity.IngredientEntity
import com.example.lb1.entity.RecipeEntity
import com.example.lb1.repositories.ingredient.dto.CreateIngredientDto
import com.example.lb1.repositories.ingredient.dto.IngredientDto
import com.example.lb1.repositories.ingredient.dto.UpdateIngredientDto
import com.example.lb1.repositories.ingredient.types.FindOneIngredientPayload
import com.example.lb1.repositories.recipe.dto.CreateRecipeDto
import com.example.lb1.repositories.recipe.dto.RecipeDto
import com.example.lb1.repositories.recipe.dto.UpdateRecipeDto
import com.example.lb1.repositories.recipe.types.FindOneRecipePayload

class RecipeRepository(private val appDao: AppDao) : RecipeBase {
  override suspend fun getAll(): List<RecipeDto> {
    return appDao.getAllRecipes().map { RecipeDto(
      it.id,
      it.name,
      it.categoryId,
    ) }
  }

  override suspend fun getOne(payload: FindOneRecipePayload): RecipeDto {
    val target = if (payload.name !== "") {
      appDao.findOneRecipe(name = payload.name!!)
    } else {
      appDao.getOneRecipe(payload.id)
    }
    return RecipeDto(
      target.id,
      target.name,
      target.categoryId,
    )
  }

  override suspend fun create(dto: CreateRecipeDto) {
    appDao.createRecipe(RecipeEntity(
        0,
        dto.name,
        dto.categoryId
      )
    )
  }

  override suspend fun update(id: Int, dto: UpdateRecipeDto): RecipeDto? {
    appDao.updateRecipe(RecipeEntity(
      id,
      dto.name,
      dto.categoryId
    ))
    val updated = appDao.getOneRecipe(id)
    return RecipeDto(
      updated.id,
      updated.name,
      updated.categoryId
    )
  }

  override suspend fun delete(id: Int): Boolean {
    val target = appDao.getOneRecipe(id)
    appDao.deleteRecipe(target)

    return appDao.getOneRecipe(id) == null
  }
}