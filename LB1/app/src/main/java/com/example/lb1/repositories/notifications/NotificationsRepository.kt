package com.example.lb1.repositories.notifications

import com.example.lb1.dao.AppDao
import com.example.lb1.entity.NotificationEntity
import com.example.lb1.repositories.notifications.dto.CreateNotificationDto
import com.example.lb1.repositories.notifications.dto.NotificationDto
import com.example.lb1.repositories.notifications.dto.UpdateNotificationDto
import com.example.lb1.repositories.notifications.types.FindOneNotificationPayload

class NotificationsBaseRepository(private val appDao: AppDao): NotificationsBase {
//  private var notifications = arrayOf<NotificationDto>(
//    NotificationDto(id = 0, title = "First notif", shortDescription = "About system integrity 1", fullDescription = "Integrity compromised 1x"),
//    NotificationDto(id = 1, title = "Second notif", shortDescription = "About system integrity 2", fullDescription = "Integrity compromised 2x"),
//    NotificationDto(id = 2, title = "Third notif", shortDescription = "About system integrity 3", fullDescription = "Integrity compromised 3x"),
//    NotificationDto(id = 3, title = "Fourth notif", shortDescription = "About system integrity 4", fullDescription = "Integrity compromised 4x"),
//  );

  override suspend fun insertAll(notifs: List<NotificationDto>) {
    appDao.insertAllNotifs(notifs.map { NotificationEntity(
      it.id,
      it.title,
      it.shortDescription,
      it.fullDescription
    ) })
  }

  override suspend fun getAll(): List<NotificationDto> {
//    return notifications;
    return appDao.getAllNotifs().map { NotificationDto(
      it.id,
      it.title,
      it.shortDescription,
      it.fullDescription
    ) }
  }

  override suspend fun getOne(payload: FindOneNotificationPayload): NotificationDto? {
//    val target = notifications.find { it.id == payload.id || it.title == payload.title }
    val target = appDao.getOneNotif(payload.id!!)
    return NotificationDto(
      target.id,
      target.title,
      target.shortDescription,
      target.fullDescription
    );
  }


  override suspend fun create(dto: CreateNotificationDto) {
//    notifications.plus(NotificationDto (
//      id = notifications.lastIndex + 1,
//      title = dto.title,
//      shortDescription = dto.shortDescription,
//      fullDescription = dto.fullDescription
//    ))
    appDao.createNotif(NotificationEntity(
      0,
      dto.title,
      dto.shortDescription,
      dto.fullDescription
    ))
  }

  override suspend fun update(dto: UpdateNotificationDto): NotificationDto? {
//    val target = notifications.find { it.id == dto.id }
//    val index = notifications.indexOf(target)
//    if (target == null || index == -1) return null;
//
//    val updated = target.copy(
//      title = dto.title ?: target.title,
//      shortDescription = dto.shortDescription ?: target.shortDescription,
//      fullDescription = dto.fullDescription ?: target.fullDescription
//    )
//
//    notifications[index] = updated
//
//    return notifications[index];
    return NotificationDto(
      dto.id,
      dto.title!!,
      dto.shortDescription!!,
      dto.fullDescription!!
    );
  }

  override suspend fun delete(id: Int): String {
//    val index = notifications.indexOfFirst { it.id == id }
//    if (index == -1) return "Cannot find notification with id $id"
//
//    notifications = (notifications.filter { it.id != id }).toTypedArray();
//
//    val target = notifications.indexOfFirst { it.id == id }
//    return if (target == -1) {
//      "Notification deleted successfully"
//    } else {
//      "There was an error deleting notification"
//    }
    val target = appDao.getOneNotif(id) ?: return "Not found";
    appDao.deleteNotif(target)
    return "Removed"
  }
}