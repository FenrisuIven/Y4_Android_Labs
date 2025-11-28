package com.example.lb1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lb1.model.Notification

class NotificationsScreenVM(): ViewModel() {
  private val _notificationsList = MutableLiveData(listOf<Notification>());
  val notificationsList: LiveData<List<Notification>> = _notificationsList;

  private fun init() {
    val titles = arrayOf(
      "New Message",
      "System Maintenance",
    );
    val shortDescriptions = arrayOf(
      "You have received a new text message.",
      "Scheduled maintenance will occur tonight at 2:00 AM.",
    );
    val longDescriptions = arrayOf(
      "You have received a new text message from <username> on <date>.\n\"Hello <username>, We've been trying to reach you concerning your vehicle's extended warranty. ...\"",
      "Scheduled maintenance will occur tonight at 2:00 AM. The service will become unavailable for a short period from 2:00AM to 6:00AM of the next day.",
    );

    var notifications = listOf<Notification>();
    for (i in 0..9) {
      notifications = notifications.plus(Notification (
        id = i,
        title = titles[i % titles.size],
        shortDescription = shortDescriptions[i % shortDescriptions.size],
        fullDescription = longDescriptions[i % longDescriptions.size]
      ))
    }

    _notificationsList.postValue(notifications);
  }

  fun getById(id: Int): Notification {
    return _notificationsList.value!!.find { it.id == id }!!;
  }
  fun getAllNotifications(): LiveData<List<Notification>> {
    if (_notificationsList.value?.isEmpty() == true) this.init();
    return notificationsList;
  }

  fun removeNotification(id: Int?) {
    if (id == null) return

    _notificationsList.postValue(
      _notificationsList.value?.filter{item ->
        item.id != id
      }
    )
  }
}