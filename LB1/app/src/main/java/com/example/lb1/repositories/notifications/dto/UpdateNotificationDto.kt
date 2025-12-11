package com.example.lb1.repositories.notifications.dto

data class UpdateNotificationDto(
  var id: Int,
  var title: String?,
  var shortDescription: String?,
  var fullDescription: String?,
)