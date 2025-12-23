package com.example.lb1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lb1.MyApp
import com.example.lb1.api.Api
import com.example.lb1.api.RetrofitClient
import com.example.lb1.model.Listable
import com.example.lb1.repositories.AppRepository
import kotlin.random.Random

class HomeScreenVM(app: Application) : AndroidViewModel(app) {
  private val repo: AppRepository = (app as MyApp).appRepo

  private val _allDataList = MutableLiveData<List<Listable>>(emptyList())
  val allDataList: LiveData<List<Listable>> = _allDataList

  suspend fun getAllData() {
    _allDataList.postValue(repo.getData())
  }
}