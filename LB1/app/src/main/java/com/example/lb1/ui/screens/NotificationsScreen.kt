package com.example.lb1.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import com.example.lb1.model.Notification
import com.example.lb1.viewmodels.NotificationsScreenVM


@Composable
fun NotificationsScreen(
  modifier: Modifier,
  viewModel: NotificationsScreenVM = viewModel()
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
      itemsIndexed(notificationsList.value!!) { index: Int, listItem: Notification ->
        NotificationsCard(
          notification = listItem,
          removeAction = { viewModel.removeNotification(index) }
        )
      }
    }
  }
}

@Composable
fun NotificationsCard(
  notification: Notification,
  removeAction: () -> Unit
) {
  Row(
    modifier = Modifier.padding(8.dp, 0.dp),
  ){
    Box(
      modifier = Modifier
        .width(8.dp)
        .height(116.dp)
        .background(Color.Gray)
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.LightGray)
          .padding(8.dp, 0.dp),
      ) {
        Text(
          text = notification.title,
          fontWeight = FontWeight.Medium,
          modifier = Modifier.weight(1f)
        )
        Box(
          modifier = Modifier
            .width(24.dp)
            .height(24.dp)
            .clickable(
              onClick = { removeAction() }
            )
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close Button",
          )
        }
      }

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(88.dp)
          .background(Color.LightGray)
          .padding(8.dp, 0.dp)
      ) {
        Text(
          text = notification.description
        )
      }
    }
  }
}