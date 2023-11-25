package com.example.mytodo.data.source.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import retrofit2.http.POST
import retrofit2.http.PUT

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list : List<T>)

    @Delete
    suspend fun delete(t : T)


}