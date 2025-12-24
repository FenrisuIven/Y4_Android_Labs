package com.example.lb1.ui.screens.recipes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lb1.repositories.category.dto.CategoryDto
import com.example.lb1.viewmodels.RecipesVM
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun NewRecipeScreen(
  modifier: Modifier,
  recipesVM: RecipesVM = viewModel(),
  onInsertSuccess: () -> Unit
) {
  var name by remember { mutableStateOf("") }

  var categories: List<CategoryDto> by remember { mutableStateOf(emptyList()) }
  var selectedOption by remember { mutableStateOf(CategoryDto(-1, "None selected")) }

  var expanded by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    categories = recipesVM.getAllCategories()
  }

  fun onClickAction () {
    if (name == "") return;
    if (selectedOption.id == -1) return;

    runBlocking {
      val res = recipesVM.createRecipe(name, categoryId = selectedOption.id);

      if (res != (-1).toLong()) onInsertSuccess()
    }
  }

  Column(
    modifier = modifier.padding(16.dp).fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    TextField(
      modifier = Modifier.fillMaxWidth(),
      value = name,
      onValueChange = { newText ->
        name = newText
      },
      label = { Text(text = "Enter recipe name") },
      singleLine = true,
    )
    ExposedDropdownMenuBox(
      modifier = Modifier.fillMaxWidth(),
      expanded = expanded,
      onExpandedChange = { expanded = it }
    ) {
      TextField(
        modifier = Modifier
          .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable)
          .fillMaxWidth(),
        value = selectedOption.name,
        onValueChange = {},
        readOnly = true,
        label = { Text("Select category") },
        trailingIcon = {
          ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors(),
      )

      ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
      ) {
        categories.forEach { item ->
          DropdownMenuItem(
            text = { Text(text = item.name) },
            onClick = {
              selectedOption = item
              expanded = false
            },
            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
          )
        }
      }
    }
    Button(
      onClick = {
        onClickAction()
      },
      modifier = Modifier.fillMaxWidth().padding(64.dp, 0.dp)
    ) {
      Text(
        text = "Create"
      )
    }
  }
}