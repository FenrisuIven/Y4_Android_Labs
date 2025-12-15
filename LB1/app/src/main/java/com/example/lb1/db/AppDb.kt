package com.example.lb1.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lb1.dao.AppDao
import com.example.lb1.entity.CategoryEntity
import com.example.lb1.entity.IngredientEntity
import com.example.lb1.entity.NotificationEntity
import com.example.lb1.entity.RecipeEntity

@Database(
  entities = [
    NotificationEntity::class,
    IngredientEntity::class,
    CategoryEntity::class,
    RecipeEntity::class,
  ],
  version = 4,
  exportSchema = true,
  autoMigrations = [
    AutoMigration(1,2),
    AutoMigration(2,3),
    AutoMigration(3,4),
  ]
)
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
        )
//          .addMigrations(MIGRATION_1_2)
//          .addMigrations(MIGRATION_2_3)
//          .addMigrations(MIGRATION_3_4)
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
}