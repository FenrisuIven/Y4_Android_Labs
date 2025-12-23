package com.example.lb1.model

data class RecipesList(
  override val id: Long = 0,
  val recipes: List<Recipe>
): Listable
