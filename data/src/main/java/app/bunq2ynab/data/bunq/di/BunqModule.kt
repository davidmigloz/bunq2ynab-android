package app.bunq2ynab.data.bunq.di

import app.bunq2ynab.data.bunq.BunqRepositoryImpl
import app.bunq2ynab.data.bunq.local.BunqLocalDataSource
import app.bunq2ynab.data.bunq.local.BunqLocalDataSourceImpl
import app.bunq2ynab.data.bunq.remote.BunqRemoteDataSource
import app.bunq2ynab.data.bunq.remote.BunqRemoteDataSourceImpl
import app.bunq2ynab.domain.repository.BunqRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BunqModule {

    @Singleton
    @Binds
    abstract fun bindBunqRepository(
        impl: BunqRepositoryImpl
    ): BunqRepository

    @Singleton
    @Binds
    abstract fun bindBunqRemoteDataSource(
        impl: BunqRemoteDataSourceImpl
    ): BunqRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindBunqLocalDataSource(
        impl: BunqLocalDataSourceImpl
    ): BunqLocalDataSource
}
