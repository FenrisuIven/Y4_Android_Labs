package com.example.lb1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lb1.MyApp
import com.example.lb1.repositories.notifications.NotificationsBaseRepository
import com.example.lb1.repositories.notifications.dto.NotificationDto
import com.example.lb1.repositories.notifications.types.FindOneNotificationPayload
import kotlinx.coroutines.launch

class NotificationsScreenVM(app: Application): AndroidViewModel(app) {
  private val repo: NotificationsBaseRepository = (app as MyApp).notifRepo
  private val _notificationsList = MutableLiveData(listOf<NotificationDto>());
  val notificationsList: LiveData<List<NotificationDto>> = _notificationsList;

  private suspend fun loadAll() {
    val target = repo.getAll().map{ NotificationDto(
      id = it.id,
      title = it.title,
      shortDescription = it.shortDescription,
      fullDescription = it.fullDescription
    ) }.toList()
    _notificationsList.postValue(target);
  }

//  private fun init(): List<NotificationDto> {
//    val titles = arrayOf(
//      "New Message",
//      "System Maintenance",
//    );
//    val shortDescriptions = arrayOf(
//      "You have received a new text message.",
//      "Scheduled maintenance will occur tonight at 2:00 AM.",
//    );
//    val longDescriptions = arrayOf(
//      "You have received a new text message from <username> on <date>.\n\"Hello <username>, We've been trying to reach you concerning your vehicle's extended warranty. ...\"",
//      "Scheduled maintenance will occur tonight at 2:00 AM. The service will become unavailable for a short period from 2:00AM to 6:00AM of the next day.",
//    );
//
//    var notifications = listOf<NotificationDto>();
//    for (i in 0..9) {
//      notifications = notifications.plus(NotificationDto (
//        id = i,
//        title = titles[i % titles.size],
//        shortDescription = shortDescriptions[i % shortDescriptions.size],
//        fullDescription = longDescriptions[i % longDescriptions.size]
//      ))
//    }
//
//    _notificationsList.postValue(notifications);
//    return notifications
//  }

  fun loadFromDB() {
    viewModelScope.launch {
      loadAll();
    }
  }

  suspend fun getById(id: Int): NotificationDto {
    repo.getOne(FindOneNotificationPayload(id, title = ""))
    return _notificationsList.value!!.find { it.id == id }!!;
  }
  suspend fun getAllNotifications(): LiveData<List<NotificationDto>> {
//    if (_notificationsList.value?.isEmpty() == true) {
//      this.init()
//    }
    if (_notificationsList.value?.isEmpty() == true) this.loadAll();
    return notificationsList;
  }
  suspend fun removeNotification(id: Int?) {
    if (id == null) return
//    _notificationsList.postValue(
//      _notificationsList.value?.filter{item ->
//        item.id != id
//      }
//    )
    repo.delete(id)
    loadAll()
  }
}