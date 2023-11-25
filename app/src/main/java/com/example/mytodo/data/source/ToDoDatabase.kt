package com.example.mytodo.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytodo.data.source.dao.ToDoDao
import com.example.mytodo.data.source.entity.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 2)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun getToDo(): ToDoDao
}