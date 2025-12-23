package com.example.lb1.ui.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lb1.types.NavigationRoute
import com.example.lb1.R

object Home: NavigationRoute {
  override val startDestination: String = "main"
  override val path: String = "home"
  override val icon: ImageVector = Icons.Default.Home
  override val iconId: Int? = null
}

object RecipesScreen: NavigationRoute {
  override val startDestination: String = "recipes"
  override val path: String = "recipes"
  override val icon: ImageVector? = null
  override val iconId: Int = R.drawable.baseline_soup_kitchen_24

  object RecipeDetailsScreen: NavigationRoute {
    override val startDestination: String = "recipes"
    override val path: String = "details?recipeId={recipeId}"
    override val icon: ImageVector? = null
    override val iconId: Int = R.drawable.baseline_soup_kitchen_24
  }

  object NewRecipeScreen: NavigationRoute {
    override val startDestination: String = "recipes"
    override val path: String = "create"
    override val icon: ImageVector = Icons.Default.Home
    override val iconId: Int? = null
  }
}

object More: NavigationRoute {
  override val startDestination: String = "main"
  override val path: String = "more"
  override val icon: ImageVector = Icons.Default.MoreVert
  override val iconId: Int? = null
}

val routes = listOf<NavigationRoute>(Home, RecipesScreen, More)