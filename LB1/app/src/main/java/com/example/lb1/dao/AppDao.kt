package com.example.lb1.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.example.lb1.entity.NotificationEntity

@Dao
interface AppDao {
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
}