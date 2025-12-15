package com.example.lb1.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "categories",
  indices = [Index(value = ["name"], unique = true)]
)
data class CategoryEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val name: String,
)
