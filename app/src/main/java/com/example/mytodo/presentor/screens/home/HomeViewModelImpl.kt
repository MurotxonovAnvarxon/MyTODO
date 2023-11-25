package com.example.mytodo.presentor.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val homeDirection: HomeDirection, private val repository: AppRepository
) : ViewModel(), HomeContract.ViewModel {
    override val uiState = MutableStateFlow<HomeContract.UIState>(HomeContract.UIState())


    init {
        repository.loadContacts().onEach {
            uiState.update { state -> state.copy(list = it) }
        }.launchIn(viewModelScope)
    }


    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.MoveToAdd -> {
                viewModelScope.launch {
                    homeDirection.moveToAdd()
                }
            }
        }
    }
}