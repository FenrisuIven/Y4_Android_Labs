package com.example.lb1.model

data class IngredientsList(
  override val id: Long = 0,
  val ingredients: List<Ingredient>
): Listable
