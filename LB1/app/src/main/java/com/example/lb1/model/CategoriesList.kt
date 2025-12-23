package com.example.lb1.model

data class CategoriesList(
  override val id: Long = 0,
  val categories: List<Category>
): Listable
