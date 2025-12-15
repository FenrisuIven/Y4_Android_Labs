package com.example.lb1.repositories.category

import com.example.lb1.dao.AppDao
import com.example.lb1.entity.IngredientEntity
import com.example.lb1.repositories.ingredient.dto.CreateIngredientDto
import com.example.lb1.repositories.ingredient.dto.IngredientDto
import com.example.lb1.repositories.ingredient.dto.UpdateIngredientDto
import com.example.lb1.repositories.ingredient.types.FindOneIngredientPayload

class CategoryRepository(private val appDao: AppDao) : CategoryBase {
  override suspend fun getAll(): List<IngredientDto> {
    return appDao.getAllIngredients().map { IngredientDto(
      it.id,
      it.name
    ) }
  }

  override suspend fun getOne(payload: FindOneIngredientPayload): IngredientDto {
    val target = if (payload.name !== null) {
      appDao.findOneIngredient(name = payload.name!!)
    } else {
      appDao.getOneIngredient(payload.id)
    }
    return IngredientDto(
      target.id,
      target.name
    )
  }

  override suspend fun create(dto: CreateIngredientDto) {
    appDao.createIngredient(IngredientEntity(
      0,
      dto.name
    ))
  }

  override suspend fun update(id: Int, dto: UpdateIngredientDto): IngredientDto? {
    appDao.updateIngredient(IngredientEntity(
      id,
      dto.name
    ))
    val updated = appDao.getOneIngredient(id)
    return IngredientDto(
      updated.id,
      updated.name
    )
  }

  override suspend fun delete(id: Int): Boolean {
    val target = appDao.getOneIngredient(id)
    appDao.deleteIngredient(target)

    return appDao.getOneIngredient(id) == null
  }
}