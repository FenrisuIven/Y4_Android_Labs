package com.example.lb1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

data class HomeUiState (
    val labelIdxValue: Int = 0
)

class HomeScreenVM() : ViewModel() {
    private val _uiState = MutableLiveData(HomeUiState())
    val uiState: LiveData<HomeUiState> = _uiState;

    fun changeState(minValue: Int = 0, maxValue: Int = 2) {
        var newIdx = _uiState.value?.labelIdxValue;
        while (newIdx == _uiState.value?.labelIdxValue) {
            newIdx = Random.nextInt(from = minValue, until = maxValue);
        }
        _uiState.postValue(_uiState.value?.copy(
            labelIdxValue = newIdx!!
        ))
    }
}