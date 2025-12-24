package com.example.lb1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lb1.MyApp
import com.example.lb1.repositories.category.CategoryRepository
import com.example.lb1.repositories.category.dto.CategoryDto
import com.example.lb1.repositories.category.types.FindOneCategoryPayload
import com.example.lb1.repositories.recipe.RecipeRepository
import com.example.lb1.repositories.recipe.dto.CreateRecipeDto
import com.example.lb1.repositories.recipe.dto.RecipeDto
import com.example.lb1.repositories.recipe.types.FindOneRecipePayload
import kotlinx.coroutines.runBlocking

class RecipesVM(app: Application): AndroidViewModel(app) {
  private val recipesRepo: RecipeRepository = (app as MyApp).recipesRepo
  private val categoriesRepo: CategoryRepository = (app as MyApp).categoriesRepo
  private val _recipesList = MutableLiveData(listOf<RecipeDto>());
  val recipesList: LiveData<List<RecipeDto>> = _recipesList;

  private suspend fun loadAll(): List<RecipeDto> {
    val target = recipesRepo.getAll().map{ RecipeDto(
      it.id,
      it.name,
      it.categoryId,
    ) }.toList()
    _recipesList.postValue(target);
    return target
  }

  fun loadFromDB() {
    runBlocking {
      loadAll();
    }
  }

  suspend fun getRecipeById(id: Int): RecipeDto {
    recipesRepo.getOne(FindOneRecipePayload(id, name = ""))

    if (recipesList.value?.isEmpty() == true) {
      val recipes = this.loadAll();
      return recipes.find { it.id == id }!!
    }
    return recipesList.value!!.find { it.id == id }!!
  }

  suspend fun getAllRecipes(): LiveData<List<RecipeDto>> {
    if (_recipesList.value?.isEmpty() == true) this.loadAll();
    return recipesList;
  }

  suspend fun getCategoryById(id: Int): CategoryDto {
    val target = categoriesRepo.getOne(FindOneCategoryPayload(id, name = ""))
    return CategoryDto(target.id, target.name)
  }

  suspend fun getCategoryByName(name: String): CategoryDto {
    val target = categoriesRepo.getOne(FindOneCategoryPayload(id = -1, name));
    return target;
  }

  suspend fun getAllCategories(): List<CategoryDto> {
    return categoriesRepo.getAll()
  }

  suspend fun createRecipe(name: String, categoryId: Int): Long {
    return recipesRepo.create(CreateRecipeDto(name, categoryId))
  }

  suspend fun removeRecipe(id: Int?) {
    if (id == null) return

    recipesRepo.delete(id)
    loadAll()
  }
}