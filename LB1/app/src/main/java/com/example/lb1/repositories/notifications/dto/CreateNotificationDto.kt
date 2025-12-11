package com.example.lb1.repositories.notifications.dto

data class CreateNotificationDto(
  var title: String,
  var shortDescription: String,
  var fullDescription: String,
)