package com.example.lb1

import android.app.Application
import com.example.lb1.db.AppDb
import com.example.lb1.repositories.AppRepository
import com.example.lb1.repositories.category.CategoryRepository
import com.example.lb1.repositories.ingredient.IngredientRepository
import com.example.lb1.repositories.recipe.RecipeRepository

class MyApp: Application() {
  private val appDb by lazy { AppDb.getDB(this) }
  val recipesRepo by lazy { RecipeRepository(appDb.appDao()) }
  val ingredientsRepo by lazy { IngredientRepository(appDb.appDao()) }
  val categoriesRepo by lazy { CategoryRepository(appDb.appDao()) }
  val appRepo by lazy { AppRepository(appDb.appDao()) }
}