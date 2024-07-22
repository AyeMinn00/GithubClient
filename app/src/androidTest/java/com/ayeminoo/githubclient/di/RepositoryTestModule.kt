package com.ayeminoo.githubclient.di

import com.ayeminoo.domain.users.UsersRepository
import com.ayeminoo.githubclient.fake.FakeUsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object RepositoryTestModule {

//    @Singleton
//    @Binds
//    abstract fun bindsFakeUsersRepository(repo: FakeUsersRepository): UsersRepository

    @Singleton
    @Provides
    fun providesFakeUsersRepository(): UsersRepository {
        return FakeUsersRepository()
    }

}