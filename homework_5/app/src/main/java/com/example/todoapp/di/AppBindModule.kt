package com.example.todoapp.di

import com.example.todoapp.manager.network.NetworkStatusListenerHelper
import com.example.todoapp.manager.network.NetworkStatusListenerHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindModule {

    @Binds
    @Singleton
    abstract fun bindNetworkStatusListenerHelper(configImpl: NetworkStatusListenerHelperImpl): NetworkStatusListenerHelper

}
