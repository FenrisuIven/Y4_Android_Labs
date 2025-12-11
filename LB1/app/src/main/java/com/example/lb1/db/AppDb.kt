package com.example.lb1.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.lb1.dao.AppDao
import com.example.lb1.entity.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1, exportSchema = true)
abstract class AppDb: RoomDatabase() {
  abstract fun appDao(): AppDao

  companion object {
    @Volatile
    private var INSTANCE: AppDb? = null

    fun getDB(ctx: Context): AppDb {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          ctx.applicationContext,
          AppDb::class.java,
          "app_database.db1"
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}