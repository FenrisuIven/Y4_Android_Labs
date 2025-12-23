package com.example.lb1.model

data class Category(
  override val id: Long = 0,
  val name: String
): Listable
