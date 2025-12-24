package com.example.lb1.ui.screens.components.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lb1.model.CategoriesList
import com.example.lb1.model.Category


@Composable
fun CategoriesCardList(list: CategoriesList) {
  Column(
    Modifier.heightIn(min = 80.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Text(
      text = "Categories",
      Modifier.fillMaxWidth(),
      fontSize = 20.sp
    )
    LazyRow(
      modifier = Modifier.fillMaxWidth().weight(1f),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
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
    horizontalArrangement = Arrangement.spacedBy(16.dp)
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