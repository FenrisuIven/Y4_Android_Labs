package com.example.lb1.repositories.details

import com.example.lb1.repositories.details.dto.CreateDetailDto
import com.example.lb1.repositories.details.dto.DetailDto
import com.example.lb1.repositories.details.dto.UpdateDetailDto
import com.example.lb1.repositories.details.types.FindOneDetailPayload

interface DetailsBase {
  suspend fun getAll(): List<DetailDto>
  suspend fun getOne(payload: FindOneDetailPayload): DetailDto?
  suspend fun insertAll(notifs: List<DetailDto>)
  suspend fun create(dto: CreateDetailDto): DetailDto?
  suspend fun update(dto: UpdateDetailDto): DetailDto?
  suspend fun delete(id: Int): String
}