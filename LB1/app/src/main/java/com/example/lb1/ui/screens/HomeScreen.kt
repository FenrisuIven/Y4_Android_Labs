package com.example.lb1.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lb1.viewmodels.HomeScreenVM
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lb1.model.CategoriesList
import com.example.lb1.model.Category
import com.example.lb1.model.Ingredient
import com.example.lb1.model.IngredientsList
import com.example.lb1.model.Listable
import com.example.lb1.model.Recipe
import com.example.lb1.model.RecipesList

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
        Log.d("DEBUG", "$listItem")
        when (listItem) {
          is RecipesList -> RecipesCardList(listItem)
          is CategoriesList -> CategoriesCardList(listItem)
          is IngredientsList -> IngredientsCardList(listItem)
          else -> {}
        }
      }
    }
  }
}

@Composable
fun RecipesCardList(list: RecipesList) {
  Column(
    Modifier.heightIn(min = 224.dp).fillMaxHeight()
  ) {
    Text(
      text = "Recipes",
      Modifier.fillMaxWidth(),
      fontSize = 20.sp
    )
    LazyColumn (
      modifier = Modifier.fillMaxWidth().weight(1f),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      items(list.recipes) {
        RecipeCard(it)
      }
    }
  }
}

@Composable
fun RecipeCard(recipe: Recipe) {
  Row(
    Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(24.dp)
  ) {
    Box(
      Modifier
        .background(Color.Red)
        .width(40.dp)
        .height(40.dp)
    ) {}
    Text(
      text = recipe.name
    )
  }
}

@Composable
fun CategoriesCardList(list: CategoriesList) {
  Column(
    Modifier.heightIn(min = 224.dp).fillMaxHeight()
  ) {
    Text(
      text = "Categories",
      Modifier.fillMaxWidth(),
      fontSize = 20.sp
    )
    LazyColumn(
      modifier = Modifier.fillMaxWidth().weight(1f),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      items(list.categories) {
        CategoryCard(it)
      }
    }
  }
}

@Composable
fun CategoryCard(category: Category) {
  Row(
    Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(24.dp)
  ) {
    Box(
      Modifier
        .background(Color.Yellow)
        .width(40.dp)
        .height(40.dp)
    ) {}
    Text(
      text = category.name
    )
  }
}

@Composable
fun IngredientsCardList(list: IngredientsList) {
  Column(
    Modifier.heightIn(min = 224.dp).fillMaxHeight()
  ) {
    Text(
      text = "Ingredients",
      Modifier.fillMaxWidth(),
      fontSize = 20.sp
    )
    LazyColumn(
      modifier = Modifier.fillMaxWidth().weight(1f),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      items(list.ingredients) {
        IngredientCard(it)
      }
    }
  }
}

@Composable
fun IngredientCard(ingredient: Ingredient) {
  Row(
    Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(24.dp)
  ) {
    Box(
      Modifier
        .background(Color.Green)
        .width(40.dp)
        .height(40.dp)
    ) {}
    Text(
      text = ingredient.name
    )
  }
}