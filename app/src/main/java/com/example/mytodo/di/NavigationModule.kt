package com.example.mytodo.di

import com.example.mytodo.utils.appNavigator.AppNavigator
import com.example.mytodo.utils.appNavigator.AppNavigatorDispatcher
import com.example.mytodo.utils.appNavigator.AppNavigatorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindAppNa(impl: AppNavigatorDispatcher): AppNavigator

    @[Binds Singleton]
    fun bindHandler(impl: AppNavigatorDispatcher): AppNavigatorHandler

}