package com.example.mytodo.domain

import com.example.mytodo.data.model.ToDoData
import com.example.mytodo.data.source.dao.ToDoDao
import com.example.mytodo.data.source.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val toDoDao: ToDoDao

) : AppRepository {
    override fun startWorker(date: Long, timeUnit: TimeUnit) {

    }

    override fun loadContacts(): Flow<List<ToDoData>> = toDoDao.getAllContacts()

    override suspend fun insert(contactData: ToDoData) {
        toDoDao.insert(contactData.toEntity())
    }
}