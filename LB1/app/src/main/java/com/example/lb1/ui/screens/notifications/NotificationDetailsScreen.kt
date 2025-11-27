package com.example.lb1.ui.screens.notifications

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lb1.viewmodels.NotificationsScreenVM

@Composable()
fun NotificationDetailsScreen(
  modifier: Modifier,
  viewModel: NotificationsScreenVM = viewModel(),
  navController: NavController
) {
  val notificationId = navController
    .getBackStackEntry("details?notifId={notifId}")
    .arguments?.getString("notifId")

  if (notificationId == null || notificationId == "") {
    Log.e("NotifID NULL", "Notification ID was either not set, or cannot be accessed")
  }

  val targetNotif = viewModel.getById(notificationId!!.toInt());

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(0.dp, 24.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .padding(8.dp, 4.dp),
      horizontalArrangement = Arrangement.Center
    ) {
      Text(
        text = targetNotif.title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
      )
    }

    Column(
      modifier = Modifier
    ) {

    }
  }
}