package com.example.lb1.ui.screens.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lb1.repositories.recipe.dto.RecipeDto
import com.example.lb1.ui.screens.recipes.components.RecipeCard
import com.example.lb1.viewmodels.RecipesVM
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun RecipesScreen(
  modifier: Modifier,
  recipesVM: RecipesVM = viewModel(),
  navController: NavController
) {
  val recipesList = recipesVM.recipesList.observeAsState()
  val scope = rememberCoroutineScope()

  runBlocking {
    recipesVM.loadFromDB()
    recipesVM.getAllRecipes()
  }

  Column(
    modifier = modifier.fillMaxWidth(),
  ) {
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier
        .weight(1f)
        .padding(0.dp, 8.dp)
    ) {
      itemsIndexed(recipesList.value!!) { _: Int, recipe: RecipeDto ->
        RecipeCard(
          recipe = recipe,
          category = runBlocking {
            recipesVM.getCategoryById(recipe.categoryId);
          },
          removeAction = {
            scope.launch {
              recipesVM.removeRecipe(recipe.id)
            }
          },
          editAction = { navController.navigate("edit?recipeId=${recipe.id}") },
          clickAction = { navController.navigate("details?recipeId=${recipe.id}") },
        )
      }
    }
    Row(
      modifier = Modifier.fillMaxWidth().padding(8.dp),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Box(
        modifier = Modifier
          .width(32.dp)
          .height(32.dp)
          .background(Color.LightGray)
          .clickable(onClick = { navController.navigate("create") }),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "New Recipe",
        )
      }
      Box(
        modifier = Modifier
          .width(32.dp)
          .height(32.dp)
          .background(Color.LightGray)
          .clickable(onClick = {
            scope.launch {
              recipesVM.updateCategories()
            }
          }),
        contentAlignment = Alignment.Center
        ) {
        Icon(
          imageVector = Icons.Default.Refresh,
          contentDescription = "Update Categories",
        )
      }
    }
  }
}