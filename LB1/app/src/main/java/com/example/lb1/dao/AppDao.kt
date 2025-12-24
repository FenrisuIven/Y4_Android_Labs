package com.example.lb1.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.lb1.entity.CategoryEntity
import com.example.lb1.entity.IngredientEntity
import com.example.lb1.entity.RecipeEntity

@Dao
interface AppDao {
  //=------------------------------------------------------------=
  //=----                  INGREDIENTS                       ----=
  @Query("SELECT * FROM ingredients")
  suspend fun getAllIngredients(): List<IngredientEntity>

  @Query("SELECT * FROM ingredients WHERE id = :id")
  suspend fun getOneIngredient(id: Int): IngredientEntity

  @Query("SELECT * FROM ingredients WHERE name = :name")
  suspend fun findOneIngredient(name: String): IngredientEntity

  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun createIngredient(ingredient: IngredientEntity)

  @Update(onConflict = OnConflictStrategy.ABORT)
  suspend fun updateIngredient(ingredient: IngredientEntity)

  @Delete
  suspend fun deleteIngredient(ingredient: IngredientEntity)

  //=------------------------------------------------------------=
  //=----                  CATEGORIES                        ----=
  @Query("SELECT * FROM categories")
  suspend fun getAllCategories(): List<CategoryEntity>

  @Query("SELECT * FROM categories WHERE id = :id")
  suspend fun getOneCategory(id: Int): CategoryEntity

  @Query("SELECT * FROM categories WHERE name = :name")
  suspend fun findOneCategory(name: String): CategoryEntity

  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun createCategory(category: CategoryEntity)

  @Update(onConflict = OnConflictStrategy.ABORT)
  suspend fun updateCategory(category: CategoryEntity)

  @Delete
  suspend fun deleteCategory(category: CategoryEntity)

  //=------------------------------------------------------------=
  //=----                  RECIPES                           ----=
  @Query("SELECT * FROM recipes")
  suspend fun getAllRecipes(): List<RecipeEntity>

  @Query("SELECT * FROM recipes WHERE id = :id")
  suspend fun getOneRecipe(id: Int): RecipeEntity

  @Query("SELECT * FROM recipes WHERE name = :name")
  suspend fun findOneRecipe(name: String): RecipeEntity

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun createRecipe(recipe: RecipeEntity): Long

  @Update(onConflict = OnConflictStrategy.ABORT)
  suspend fun updateRecipe(recipe: RecipeEntity)

  @Delete
  suspend fun deleteRecipe(recipe: RecipeEntity)
}