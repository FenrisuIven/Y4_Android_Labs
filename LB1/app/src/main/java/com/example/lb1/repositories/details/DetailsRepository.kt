package com.example.lb1.repositories.details

import com.example.lb1.dao.AppDao
import com.example.lb1.repositories.details.dto.CreateDetailDto
import com.example.lb1.repositories.details.dto.DetailDto
import com.example.lb1.repositories.details.dto.UpdateDetailDto
import com.example.lb1.repositories.details.types.FindOneDetailPayload

class DetailsRepository(private val appDao: AppDao): DetailsBase  {
  override suspend fun getAll(): List<DetailDto> {
    TODO("Not yet implemented")
  }

  override suspend fun getOne(payload: FindOneDetailPayload): DetailDto? {
    TODO("Not yet implemented")
  }

  override suspend fun insertAll(notifs: List<DetailDto>) {
    TODO("Not yet implemented")
  }

  override suspend fun create(dto: CreateDetailDto): DetailDto? {
    TODO("Not yet implemented")
  }

  override suspend fun update(dto: UpdateDetailDto): DetailDto? {
    TODO("Not yet implemented")
  }

  override suspend fun delete(id: Int): String {
    TODO("Not yet implemented")
  }
}