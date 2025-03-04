package com.example.notimanager.presentation.stateholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notimanager.domain.usecase.FilteredNotificationUseCase
import com.example.notimanager.presentation.stateholder.state.FilteredNotificationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilteredNotificationViewModel @Inject constructor(
    private val filteredNotificationUseCase: FilteredNotificationUseCase
): ViewModel() {
    private val _filteredNotiState = MutableLiveData<FilteredNotificationState>()
    val filteredNotiState: LiveData<FilteredNotificationState> get() = _filteredNotiState

    init {
        loadFilteredNoti()
    }

    fun loadFilteredNoti() {
        viewModelScope.launch {
            _filteredNotiState.value = FilteredNotificationState(isLoading = true)

            try {
                val filteredList = filteredNotificationUseCase.getFilteredList()
                _filteredNotiState.value = FilteredNotificationState(filteredList = filteredList)
            } catch (e: Exception) {
                _filteredNotiState.value = FilteredNotificationState(error = e.message)
            }
        }
    }

    fun insertFilteredNoti(appNane: String, title: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            filteredNotificationUseCase.insertFiltered(appNane, title)
            onComplete()
        }
    }

    fun deleteFilteredNoti(id: Long, onComplete: () -> Unit) {
        viewModelScope.launch {
            filteredNotificationUseCase.deleteById(id)
            onComplete()
        }
    }
}