package com.ayeminoo.githubclient.di

import com.ayeminoo.data.users.repository.DefaultUsersRepository
import com.ayeminoo.domain.users.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUsersRepository(repo: DefaultUsersRepository): UsersRepository
}
