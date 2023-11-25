package com.example.mytodo.presentor.screens.add

interface AddContract {

    interface ViewModel {
        fun onEventDispatcher(intent: Intent)
    }

    interface Intent {
        data class SaveData(val title: String, val description: String, val time: Long):Intent
    }
}