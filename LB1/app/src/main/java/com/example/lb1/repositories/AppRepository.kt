package com.example.lb1.repositories

import android.util.Log
import com.example.lb1.api.Api
import com.example.lb1.api.RetrofitClient
import com.example.lb1.dao.AppDao
import com.example.lb1.model.CategoriesList
import com.example.lb1.model.Category
import com.example.lb1.model.Ingredient
import com.example.lb1.model.IngredientsList
import com.example.lb1.model.Listable
import com.example.lb1.model.Recipe
import com.example.lb1.model.RecipesList

class AppRepository(private val appDao: AppDao) {
  private val retrofit = RetrofitClient.get()
  private val apiClient = retrofit.create(Api::class.java)

  suspend fun getData(): List<Listable> {
    var lists = listOf<Listable>()

    val recipes: List<Recipe> = apiClient.getRecipes().body()!!.map {
      Recipe(id = it.id.toLong(), name = it.name, categoryId = it.categoryId)
    }
    val categories: List<Category> = apiClient.getCategories().body()!!.map {
      Category(id = it.id.toLong(), name = it.name,)
    }
    val ingredients: List<Ingredient> = apiClient.getIngredients().body()!!.map {
      Ingredient(id = it.id.toLong(), name = it.name)
    }

    lists = lists.plus(RecipesList(0, recipes))
    lists = lists.plus(CategoriesList(1, categories))
    lists = lists.plus(IngredientsList(2, ingredients))

    return lists;
  }
}