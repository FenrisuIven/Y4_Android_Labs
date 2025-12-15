package com.example.lb1.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.lb1.entity.IngredientEntity
import com.example.lb1.entity.NotificationEntity
import com.example.lb1.repositories.ingredient.types.FindOneIngredientPayload

@Dao
interface AppDao {
  //=------------------------------------------------------------=
  //=----                  NOTIFICATIONS                     ----=
  @Query("SELECT * FROM notifications")
  suspend fun getAllNotifs(): List<NotificationEntity>

  @Query("SELECT * FROM notifications WHERE id = :id")
  suspend fun getOneNotif(id: Int): NotificationEntity

  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun createNotif(notif: NotificationEntity)

  @Delete
  suspend fun deleteNotif(notif: NotificationEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllNotifs(notifs: List<NotificationEntity>)

  //=------------------------------------------------------------=
  //=----                  INGREDIENTS                       ----=
  @Query("SELECT * FROM ingredients")
  suspend fun getAllIngredients(): List<IngredientEntity>

  @Query("SELECT * FROM ingredients WHERE id = :id")
  suspend fun getOneIngredient(id: Int): IngredientEntity

  @Query("SELECT * FROM ingredients WHERE name = :name")
  suspend fun findOneIngredient(name: String): IngredientEntity

  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun createIngredient(ingredient: IngredientEntity)

  @Update(onConflict = OnConflictStrategy.ABORT)
  suspend fun updateIngredient(ingredient: IngredientEntity)

  @Delete
  suspend fun deleteIngredient(ingredient: IngredientEntity)
}