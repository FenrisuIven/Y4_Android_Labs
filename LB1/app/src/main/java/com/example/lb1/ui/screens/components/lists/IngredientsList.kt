package com.example.lb1.ui.screens.components.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lb1.model.Ingredient
import com.example.lb1.model.IngredientsList
import com.example.lb1.R


@Composable
fun IngredientsCardList(list: IngredientsList) {
  Column(
    Modifier.heightIn(min = 304.dp).fillMaxHeight(),
    verticalArrangement = Arrangement.spacedBy(10.dp)
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
        .height(40.dp),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = ImageVector.vectorResource(R.drawable.outline_grocery_24),
        contentDescription = "Ingredient Card",
        tint = Color.hsl(120f, 0.830f, 0.32f)
      )
    }
    Text(
      text = ingredient.name
    )
  }
}