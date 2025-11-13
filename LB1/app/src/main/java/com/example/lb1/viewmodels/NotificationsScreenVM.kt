package com.example.lb1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lb1.model.Notification

class NotificationsScreenVM(): ViewModel() {
  private val _notificationsList = MutableLiveData(listOf<Notification>());
  val notificationsList: LiveData<List<Notification>> = _notificationsList;

  private fun init() {
    _notificationsList.postValue(listOf(
      Notification(title = "New Message", description = "You have received a new text message."),
      Notification(title = "System Maintenance", description = "Scheduled maintenance will occur tonight at 2:00 AM."),
      Notification(title = "New Message", description = "You have received a new text message."),
      Notification(title = "System Maintenance", description = "Scheduled maintenance will occur tonight at 2:00 AM."),
      Notification(title = "New Message", description = "You have received a new text message."),
      Notification(title = "System Maintenance", description = "Scheduled maintenance will occur tonight at 2:00 AM."),
      Notification(title = "New Message", description = "You have received a new text message."),
      Notification(title = "System Maintenance", description = "Scheduled maintenance will occur tonight at 2:00 AM."),
      Notification(title = "New Message", description = "You have received a new text message."),
      Notification(title = "System Maintenance", description = "Scheduled maintenance will occur tonight at 2:00 AM."),
    ));
  }

  fun getAllNotifications(): LiveData<List<Notification>> {
    if (_notificationsList.value?.isEmpty() == true) this.init();
    return notificationsList;
  }

  fun removeNotification(idx: Int) {
    if (idx == null) return

    _notificationsList.postValue(
      _notificationsList.value?.filterIndexed{index, _ ->
        index != idx
      }
    )
  }
}