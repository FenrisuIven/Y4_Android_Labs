package com.example.lb1.repositories.recipe

import com.example.lb1.api.Api
import com.example.lb1.api.RetrofitClient
import com.example.lb1.dao.AppDao
import com.example.lb1.entity.RecipeEntity
import com.example.lb1.repositories.recipe.dto.CreateRecipeDto
import com.example.lb1.repositories.recipe.dto.RecipeDto
import com.example.lb1.repositories.recipe.dto.UpdateRecipeDto
import com.example.lb1.repositories.recipe.types.FindOneRecipePayload

class RecipeRepository(private val appDao: AppDao) : RecipeBase {
  private val retrofit = RetrofitClient.get();
  private val apiClient = retrofit.create(Api::class.java)

  suspend fun getAllApi(): List<RecipeDto> {
    val recipesResponse = apiClient.getRecipes();
    return if (recipesResponse.isSuccessful) {
      recipesResponse.body()?:emptyList()
    } else {
      emptyList()
    }
  }

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

  override suspend fun create(dto: CreateRecipeDto): Long  {
    val insertedId = appDao.createRecipe(RecipeEntity(
        0,
        dto.name,
        dto.categoryId
      )
    )
    return insertedId
  }

  override suspend fun update(id: Int, dto: UpdateRecipeDto) {
    appDao.updateRecipe(RecipeEntity(
      id,
      dto.name,
      dto.categoryId
    ))
  }

  override suspend fun delete(id: Int) {
    val target = appDao.getOneRecipe(id)
    appDao.deleteRecipe(target)
  }
}