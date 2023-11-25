package com.example.mytodo.di

import com.example.mytodo.domain.AppRepository
import com.example.mytodo.domain.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindRepo(impl: AppRepositoryImpl): AppRepository


}