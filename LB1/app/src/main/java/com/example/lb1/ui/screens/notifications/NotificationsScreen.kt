package com.example.lb1.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lb1.repositories.notifications.dto.NotificationDto
import com.example.lb1.ui.components.NotificationsCard
import com.example.lb1.viewmodels.RecipesVM
import kotlinx.coroutines.launch

@Composable
fun NotificationsScreen(
  modifier: Modifier,
  viewModel: RecipesVM = viewModel(),
  navController: NavController
) {
  val notificationsList = viewModel.recipesList.observeAsState()

  val scope = rememberCoroutineScope()

  LaunchedEffect(key1 = "home") {
    viewModel.loadFromDB();
    viewModel.getAllNotifications()
  }

  Column(
    modifier = modifier.fillMaxWidth(),
  ) {
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier
        .weight(1f)
        .padding(0.dp, 8.dp)
    ) {
      itemsIndexed(notificationsList.value!!) { _: Int, listItem: NotificationDto ->
        NotificationsCard(
          notification = listItem,
          removeAction = {
            scope.launch {
              viewModel.removeNotification(listItem.id)
            }
          },
          clickAction = { navController.navigate("details?notifId=${listItem.id}") }
        )
      }
    }
      LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .padding(8.dp, 0.dp)
    ) {
      itemsIndexed(notificationsList.value!!) { _: Int, listItem: NotificationDto ->
        Box(
          modifier = Modifier
            .background(Color.LightGray)
            .height(64.dp)
            .width(64.dp)
        ) {
          Text(
            text = "${listItem.title.substring(0, 3)}...",
            modifier = Modifier.align(Alignment.Center)
          )
        }
      }
    }
  }
}