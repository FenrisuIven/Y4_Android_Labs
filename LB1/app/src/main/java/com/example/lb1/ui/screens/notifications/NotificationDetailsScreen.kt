package com.example.lb1.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lb1.repositories.notifications.dto.NotificationDto
import com.example.lb1.viewmodels.NotificationsScreenVM

@Composable()
fun NotificationDetailsScreen(
  modifier: Modifier,
  notificationId: Int,
  viewModel: NotificationsScreenVM = viewModel()
) {
  var target: NotificationDto? = null
  LaunchedEffect(key1 = notificationId) {
    target = viewModel.getById(notificationId)
  }

  Column(
    modifier = modifier
      .fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .padding(8.dp, 4.dp),
      horizontalArrangement = Arrangement.Center
    ) {
      Text(
        text = target?.title!!,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
      )
    }

    Column(
      modifier = Modifier
        .fillMaxHeight()
        .padding(0.dp, 8.dp)
        .background(Color.LightGray)
    ) {
      Text(
        text = target?.fullDescription!!,
        modifier = Modifier
          .padding(8.dp)
      )
    }
  }
}