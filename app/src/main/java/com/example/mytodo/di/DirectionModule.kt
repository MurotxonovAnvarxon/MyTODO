package com.example.mytodo.di

import com.example.mytodo.presentor.screens.add.AddDirection
import com.example.mytodo.presentor.screens.add.AddDirectionImpl
import com.example.mytodo.presentor.screens.home.HomeDirection
import com.example.mytodo.presentor.screens.home.HomeDirectionImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {


    @[Binds Singleton]
    fun bindAddDirection(impl: AddDirectionImpl): AddDirection

    @[Binds Singleton]
    fun bindHomeDirection(impl: HomeDirectionImpl): HomeDirection
}