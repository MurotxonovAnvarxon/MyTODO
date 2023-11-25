package com.example.mytodo.domain

import com.example.mytodo.data.model.ToDoData
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit


interface AppRepository {
    fun startWorker(date: Long, timeUnit: TimeUnit)

    fun loadContacts(): Flow<List<ToDoData>>


    suspend fun insert(contactData: ToDoData)
}