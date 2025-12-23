package com.example.lb1.model

data class Recipe(
  override val id: Long = 0,
  val name: String,
  val categoryId: Int,
) : Listable
