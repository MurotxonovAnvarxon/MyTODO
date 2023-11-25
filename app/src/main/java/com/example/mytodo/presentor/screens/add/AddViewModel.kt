package com.example.mytodo.presentor.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.data.model.ToDoData
import com.example.mytodo.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddViewModel @Inject constructor(
    private val direction: AddDirection,
    private val repository: AppRepository
) : ViewModel(), AddContract.ViewModel {

    override fun onEventDispatcher(intent: AddContract.Intent) {
        when (intent) {
            is AddContract.Intent.SaveData -> {
                viewModelScope.launch {
                    val contactData = ToDoData(0, intent.title, intent.description, intent.time)
                    repository.insert(contactData)
                    direction.back()
                }
            }
        }
    }
}