package com.example.lb1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lb1.viewmodels.HomeScreenVM
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lb1.model.CategoriesList
import com.example.lb1.model.IngredientsList
import com.example.lb1.model.RecipesList
import com.example.lb1.ui.screens.components.lists.CategoriesCardList
import com.example.lb1.ui.screens.components.lists.IngredientsCardList
import com.example.lb1.ui.screens.components.lists.RecipesCardList

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeScreenVM = viewModel()
) {
  val allDataList by viewModel.allDataList.observeAsState(emptyList())

  Column(modifier = modifier.fillMaxHeight()) {
    LaunchedEffect(Unit) {
      viewModel.getAllData()
    }

    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
      items(allDataList) { listItem ->
        when (listItem) {
          is CategoriesList -> CategoriesCardList(listItem)
          is RecipesList -> RecipesCardList(listItem)
          is IngredientsList -> IngredientsCardList(listItem)
          else -> {}
        }
      }
    }
  }
}
