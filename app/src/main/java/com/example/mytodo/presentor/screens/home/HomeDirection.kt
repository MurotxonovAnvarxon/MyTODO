package com.example.mytodo.presentor.screens.home

import com.example.mytodo.data.model.ToDoData
import com.example.mytodo.presentor.screens.add.AddScreen
import com.example.mytodo.presentor.screens.edit.EditScreen
import com.example.mytodo.utils.appNavigator.AppNavigator
import javax.inject.Inject

interface HomeDirection {
    suspend fun moveToAdd()
    suspend fun moveToEdit(toDoData: ToDoData)
    suspend fun moveToSettings()
}


class HomeDirectionImpl @Inject constructor(
    private val navigation: AppNavigator
) : HomeDirection {
    override suspend fun moveToAdd() {
        navigation.openWithSave(AddScreen())
    }

    override suspend fun moveToEdit(toDoData: ToDoData) {
navigation.openWithSave(EditScreen(toDoData))
    }

    override suspend fun moveToSettings() {

    }

}