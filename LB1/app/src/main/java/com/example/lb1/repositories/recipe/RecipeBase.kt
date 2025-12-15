package com.example.lb1.repositories.recipe

import com.example.lb1.repositories.ingredient.dto.CreateIngredientDto
import com.example.lb1.repositories.ingredient.dto.IngredientDto
import com.example.lb1.repositories.ingredient.dto.UpdateIngredientDto
import com.example.lb1.repositories.ingredient.types.FindOneIngredientPayload

interface RecipeBase {
    suspend fun getAll(): List<IngredientDto>
    suspend fun getOne(payload: FindOneIngredientPayload): IngredientDto
    suspend fun create(dto: CreateIngredientDto)
    suspend fun update(id: Int, dto: UpdateIngredientDto): IngredientDto?
    suspend fun delete(id: Int): Boolean
}