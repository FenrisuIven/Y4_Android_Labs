package com.example.lb1.repositories.category

import android.util.Log
import com.example.lb1.dao.AppDao
import com.example.lb1.entity.CategoryEntity
import com.example.lb1.entity.IngredientEntity
import com.example.lb1.repositories.category.dto.CategoryDto
import com.example.lb1.repositories.category.dto.CreateCategoryDto
import com.example.lb1.repositories.category.dto.UpdateCategoryDto
import com.example.lb1.repositories.category.types.FindOneCategoryPayload
import com.example.lb1.repositories.ingredient.dto.CreateIngredientDto
import com.example.lb1.repositories.ingredient.dto.IngredientDto
import com.example.lb1.repositories.ingredient.dto.UpdateIngredientDto
import com.example.lb1.repositories.ingredient.types.FindOneIngredientPayload

class CategoryRepository(private val appDao: AppDao) : CategoryBase {
  override suspend fun getAll(): List<CategoryDto> {
    return appDao.getAllCategories().map { CategoryDto(
      it.id,
      it.name
    ) }
  }

  override suspend fun getOne(payload: FindOneCategoryPayload): CategoryDto {
    val target = if (payload.name !== "") {
      appDao.findOneCategory(name = payload.name!!)
    } else {
      appDao.getOneCategory(payload.id)
    }
    return CategoryDto(
      target.id,
      target.name
    )
  }

  override suspend fun create(dto: CreateCategoryDto) {
    appDao.createCategory(CategoryEntity(
      0,
      dto.name
    ))
  }

  override suspend fun update(id: Int, dto: UpdateCategoryDto): CategoryDto? {
    appDao.updateCategory(CategoryEntity(
      id,
      dto.name
    ))
    val updated = appDao.getOneCategory(id)
    return CategoryDto(
      updated.id,
      updated.name
    )
  }

  override suspend fun delete(id: Int): Boolean {
    val target = appDao.getOneCategory(id)
    appDao.deleteCategory(target)

    return appDao.getOneIngredient(id) == null
  }
}