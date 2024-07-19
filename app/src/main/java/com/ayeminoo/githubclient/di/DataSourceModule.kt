package com.ayeminoo.githubclient.di

import com.ayeminoo.data.users.remote.DefaultUsersDataSource
import com.ayeminoo.data.users.remote.UsersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindUsersDataSource(dataSource: DefaultUsersDataSource): UsersDataSource

}