package com.example.mytodo.presentor.screens.add

import com.example.mytodo.utils.appNavigator.AppNavigator
import javax.inject.Inject

interface AddDirection {

    suspend fun back()
}


class AddDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : AddDirection {
    override suspend fun back() {
        navigator.back()
    }

}