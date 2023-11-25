package com.example.mytodo.di

import android.content.Context
import androidx.room.Room
import com.example.mytodo.data.source.ToDoDatabase
import com.example.mytodo.data.source.dao.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ToDoDatabase =
        Room.databaseBuilder(context, ToDoDatabase::class.java, "Contact.db").build()

    @Provides
    fun provideContactDao(contactDatabase: ToDoDatabase): ToDoDao =
        contactDatabase.getToDo()

}