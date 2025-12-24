package com.example.lb1.ui.screens.recipes.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lb1.repositories.category.dto.CategoryDto
import com.example.lb1.repositories.recipe.dto.RecipeDto

@Composable
fun RecipeCard(
  recipe: RecipeDto,
  category: CategoryDto,
  removeAction: () -> Unit,
  editAction: () -> Unit,
  clickAction: () -> Unit
) {
  Row(
    modifier = Modifier
      .padding(8.dp, 0.dp)
      .clickable(onClick = { clickAction() }),
  ){
    Box(
      modifier = Modifier
        .width(8.dp)
        .height(64.dp)
        .background(Color.Gray)
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.LightGray)
          .padding(8.dp, 0.dp),
      ) {
        Text(
          text = recipe.name,
          fontWeight = FontWeight.Medium,
          modifier = Modifier.weight(1f)
        )
        Box(
          modifier = Modifier
            .width(24.dp)
            .height(24.dp)
            .clickable(onClick = { editAction() })
        ) {
          Icon(
            imageVector = Icons.Default.Create,
            contentDescription = "Edit Button",
          )
        }
        Box(
          modifier = Modifier
            .width(24.dp)
            .height(24.dp)
            .clickable(onClick = { removeAction() })
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close Button",
          )
        }
      }

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(36.dp)
          .background(Color.LightGray)
          .padding(8.dp, 0.dp)
      ) {
        Text(
          text = category.name
        )
      }
    }
  }
}