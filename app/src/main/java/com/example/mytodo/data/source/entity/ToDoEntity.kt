package com.example.mytodo.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytodo.data.model.ToDoData

@Entity(tableName = "TODOs")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val time: Long
)


fun ToDoData.toEntity(): ToDoEntity =
    ToDoEntity(id, title, description, time)

