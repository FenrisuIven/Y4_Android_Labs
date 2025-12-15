package com.example.lb1.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
  tableName = "recipes",
  foreignKeys = [ForeignKey(
    entity = CategoryEntity::class,
    parentColumns = ["id"],
    childColumns = ["category_id"],
    onDelete = ForeignKey.SET_NULL,
  )]
)
data class RecipeEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  val name: String,

  @ColumnInfo(name = "category_id")
  val categoryId: Int,
)
