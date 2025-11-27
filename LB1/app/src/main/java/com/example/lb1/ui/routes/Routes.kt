package com.example.lb1.ui.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lb1.types.NavigationRoute

object Home: NavigationRoute {
  override val startDestination: String = "main"
  override val path: String = "home"
  override val icon: ImageVector = Icons.Default.Home
}

object NotificationScreen: NavigationRoute {
  override val startDestination: String = "notifications"
  override val path: String = "notifications"
  override val icon: ImageVector = Icons.Default.Notifications

  object NotificationDetailsScreen: NavigationRoute {
    override val startDestination: String = "notifications"
    override val path: String = "details?notifId={notifId}"
    override val icon: ImageVector = Icons.Default.Notifications
  }
}

object More: NavigationRoute {
  override val startDestination: String = "main"
  override val path: String = "more"
  override val icon: ImageVector = Icons.Default.MoreVert
}

val routes = listOf<NavigationRoute>(Home, NotificationScreen, More)