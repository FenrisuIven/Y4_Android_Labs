package com.example.lb1

import android.app.Application
import com.example.lb1.db.AppDb
import com.example.lb1.repositories.notifications.NotificationsBaseRepository

class MyApp: Application() {
  private val appDb by lazy { AppDb.getDB(this) }
  val notifRepo by lazy { NotificationsBaseRepository(appDb.appDao()) }
}