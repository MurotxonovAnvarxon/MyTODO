package com.example.mytodo.presentor.screens.home

import com.example.mytodo.data.model.ToDoData
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {

    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }


    data class UIState(
        val list: List<ToDoData> = listOf()
    )

    interface Intent {
        object MoveToAdd : Intent
        data class Delete(val toDoData: ToDoData) : Intent
        data class Edit(val toDoData: ToDoData) : Intent
        object Settings : Intent
    }

}