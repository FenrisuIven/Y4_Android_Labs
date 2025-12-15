package com.example.lb1.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "recipes")
data class RecipeEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val name: String,

  @Relation(parentColumn = "id", entityColumn = "category_id")
  val category: CategoryEntity
)
