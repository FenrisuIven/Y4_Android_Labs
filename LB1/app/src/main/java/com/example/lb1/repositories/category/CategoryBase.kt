package com.example.lb1.repositories.category

import com.example.lb1.repositories.category.dto.CategoryDto
import com.example.lb1.repositories.category.dto.CreateCategoryDto
import com.example.lb1.repositories.category.dto.UpdateCategoryDto
import com.example.lb1.repositories.category.types.FindOneCategoryPayload
import com.example.lb1.repositories.ingredient.dto.CreateIngredientDto
import com.example.lb1.repositories.ingredient.dto.IngredientDto
import com.example.lb1.repositories.ingredient.dto.UpdateIngredientDto
import com.example.lb1.repositories.ingredient.types.FindOneIngredientPayload

interface CategoryBase {
    suspend fun getAll(): List<CategoryDto>
    suspend fun getOne(payload: FindOneCategoryPayload): CategoryDto
    suspend fun create(dto: CreateCategoryDto)
    suspend fun update(id: Int, dto: UpdateCategoryDto): CategoryDto?
    suspend fun delete(id: Int): Boolean
}