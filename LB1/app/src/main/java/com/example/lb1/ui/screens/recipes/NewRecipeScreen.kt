package com.example.lb1.ui.screens.recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lb1.viewmodels.RecipesVM
import kotlinx.coroutines.runBlocking

@Composable()
fun NewRecipeScreen(
  modifier: Modifier,
  recipesVM: RecipesVM = viewModel(),
) {
  var name by remember { mutableStateOf("") }
  var categoryName by remember { mutableStateOf("") }

  fun onClickAction () {
    if (name === "") return;
    if (categoryName === "") return;

    runBlocking {
      val category = recipesVM.getCategoryByName(categoryName);

      recipesVM.createRecipe(name, categoryId = category.id);
    }
  }

  Column(
    modifier = modifier,
  ) {
    TextField(
      value = name,
      onValueChange = { newText ->
        name = newText
      },
      label = { Text(text = "Enter recipe name") },
      singleLine = true
    )
    TextField(
      value = categoryName,
      onValueChange = { newText ->
        categoryName = newText
      },
      label = { Text(text = "Enter category name") },
      singleLine = true
    )
    Button(
      onClick = {
        onClickAction()
      }
    ) {
      Text(
        text = "Create"
      )
    }
  }
}