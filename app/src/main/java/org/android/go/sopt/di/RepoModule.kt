package org.android.go.sopt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.repoImpl.HomeRepoImpl
import org.android.go.sopt.data.repoImpl.SignRepoImpl
import org.android.go.sopt.domain.HomeRepository
import org.android.go.sopt.domain.SignRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun providesSignRepo(repoImpl: SignRepoImpl): SignRepository

    @Singleton
    @Binds
    abstract fun providesHomeRepo(repoImpl: HomeRepoImpl): HomeRepository

}