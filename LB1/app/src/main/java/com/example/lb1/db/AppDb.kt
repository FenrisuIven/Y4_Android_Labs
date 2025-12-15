package com.example.lb1.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lb1.dao.AppDao
import com.example.lb1.entity.CategoryEntity
import com.example.lb1.entity.IngredientEntity
import com.example.lb1.entity.NotificationEntity

val MIGRATION_1_2 = object: Migration(1,2) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("""
        CREATE TABLE IF NOT EXISTS ingredients (
          id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
          name TEXT NOT NULL
        )
      """.trimIndent()
    )
    db.execSQL("""
        CREATE UNIQUE INDEX IF NOT EXISTS `index_ingredients_name` ON ingredients (`name`)
      """.trimIndent()
    )
  }
}

val MIGRATION_2_3 = object: Migration(2,3) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("""
        CREATE TABLE IF NOT EXISTS categories (
          id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
          name TEXT NOT NULL
        )
      """.trimIndent()
    )
    db.execSQL("""
        CREATE UNIQUE INDEX IF NOT EXISTS `index_categories_name` ON categories (`name`)
      """.trimIndent()
    )
  }
}

@Database(
  entities = [
    NotificationEntity::class,
    IngredientEntity::class,
    CategoryEntity::class
  ],
  version = 3,
  exportSchema = true
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
          .addMigrations(MIGRATION_1_2)
          .addMigrations(MIGRATION_2_3)
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
}