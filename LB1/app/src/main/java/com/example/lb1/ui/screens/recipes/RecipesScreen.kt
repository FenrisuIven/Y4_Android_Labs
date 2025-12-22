package com.example.lb1.ui.screens.recipes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lb1.repositories.category.dto.CategoryDto
import com.example.lb1.repositories.recipe.dto.RecipeDto
import com.example.lb1.ui.components.RecipeCard
import com.example.lb1.viewmodels.ViewRecipesVM
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun RecipesScreen(
  modifier: Modifier,
  recipesVM: ViewRecipesVM = viewModel(),
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
//            CategoryDto(0, "Placeholder")
          },
//          category = CategoryDto(0, "Placeholder"),
          removeAction = {
            scope.launch {
              recipesVM.removeRecipe(recipe.id)
            }
          },
          clickAction = { navController.navigate("details?recipeId=${recipe.id}") },

        )
      }
    }
//    LazyRow(
//      horizontalArrangement = Arrangement.spacedBy(8.dp),
//      modifier = Modifier
//        .padding(8.dp, 0.dp)
//    ) {
//      itemsIndexed(recipesList.value!!) { _: Int, recipe: RecipeDto ->
//        Box(
//          modifier = Modifier
//            .background(Color.LightGray)
//            .height(64.dp)
//            .width(64.dp)
//        ) {
//          Text(
//            text = "${recipe.name.substring(0, 3)}...",
//            modifier = Modifier.align(Alignment.Center)
//          )
//        }
//      }
//    }
  }
}