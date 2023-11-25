package com.example.mytodo.data.source.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.mytodo.data.model.ToDoData
import com.example.mytodo.data.source.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao : BaseDao<ToDoEntity> {

    @Query("SELECT * FROM TODOs")
    fun getAllContacts(): Flow<List<ToDoData>>

}