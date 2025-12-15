package com.example.lb1.types

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

interface NavigationRoute {
  val startDestination: String
  val path: String
  val icon: ImageVector?
  val iconId: Int?
}