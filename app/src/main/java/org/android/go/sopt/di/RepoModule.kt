package org.android.go.sopt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.domain.SignRepository
import org.android.go.sopt.model.repoImpl.SignRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesSignRepo(repoImpl: SignRepoImpl): SignRepository

}