package com.example.lb1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lb1.MyApp
import com.example.lb1.repositories.recipe.RecipeRepository
import com.example.lb1.repositories.recipe.dto.RecipeDto
import com.example.lb1.repositories.recipe.types.FindOneRecipePayload
import kotlinx.coroutines.launch

class RecipesVM(app: Application): AndroidViewModel(app) {
  private val repo: RecipeRepository = (app as MyApp).recipesRepo
  private val _recipesList = MutableLiveData(listOf<RecipeDto>());
  val recipesList: LiveData<List<RecipeDto>> = _recipesList;

  private suspend fun loadAll() {
    val target = repo.getAll().map{ RecipeDto(
      it.id,
      it.name,
      it.categoryId,
    ) }.toList()
    _recipesList.postValue(target);
  }

  fun loadFromDB() {
    viewModelScope.launch {
      loadAll();
    }
  }

  suspend fun getById(id: Int): RecipeDto {
    repo.getOne(FindOneRecipePayload(id, name = ""))
    return _recipesList.value!!.find { it.id == id }!!;
  }
  suspend fun getAllNotifications(): LiveData<List<RecipeDto>> {
    if (_recipesList.value?.isEmpty() == true) this.loadAll();
    return recipesList;
  }
  suspend fun removeNotification(id: Int?) {
    if (id == null) return

    repo.delete(id)
    loadAll()
  }
}