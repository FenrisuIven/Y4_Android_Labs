package com.example.lb1.ui.screens.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lb1.model.Notification
import com.example.lb1.ui.components.NotificationsCard
import com.example.lb1.viewmodels.NotificationsScreenVM

@Composable
fun NotificationsScreen(
  modifier: Modifier,
  viewModel: NotificationsScreenVM = viewModel(),
  navController: NavController
) {
  val notificationsList = viewModel.notificationsList.observeAsState()

  SideEffect {
    viewModel.getAllNotifications()
  }
  Column(
    modifier = modifier.fillMaxWidth(),
  ) {
    Text(
      text = "Notifications",
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(0.dp, 16.dp)
    )

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier
        .weight(1f)
        .padding(0.dp, 8.dp)
    ) {
      itemsIndexed(notificationsList.value!!) { _: Int, listItem: Notification ->
        NotificationsCard(
          notification = listItem,
          removeAction = { viewModel.removeNotification(listItem.id) },
          clickAction = { navController.navigate("details?notifId=${listItem.id}") }
        )
      }
    }
  }
}