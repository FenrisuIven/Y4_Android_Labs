package com.example.lb1.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,

  val title: String,

  @ColumnInfo(name = "short_description")
  val shortDescription: String,

  @ColumnInfo(name = "full_description")
  val fullDescription: String,
)