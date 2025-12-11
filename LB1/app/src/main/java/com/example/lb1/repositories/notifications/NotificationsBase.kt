package com.example.lb1.repositories.notifications

import com.example.lb1.repositories.notifications.dto.CreateNotificationDto
import com.example.lb1.repositories.notifications.dto.NotificationDto
import com.example.lb1.repositories.notifications.dto.UpdateNotificationDto
import com.example.lb1.repositories.notifications.types.FindOneNotificationPayload

interface NotificationsBase {
  suspend fun getAll(): List<NotificationDto>
  suspend fun getOne(payload: FindOneNotificationPayload): NotificationDto?
  suspend fun insertAll(notifs: List<NotificationDto>)
  suspend fun create(dto: CreateNotificationDto)
  suspend fun update(dto: UpdateNotificationDto): NotificationDto?
  suspend fun delete(id: Int): String
}