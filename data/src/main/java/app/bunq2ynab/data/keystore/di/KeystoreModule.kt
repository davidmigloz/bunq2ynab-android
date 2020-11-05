package app.bunq2ynab.data.keystore.di

import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.BunqRepositoryImpl
import app.bunq2ynab.data.bunq.local.BunqLocalDataSource
import app.bunq2ynab.data.bunq.local.BunqLocalDataSourceImpl
import app.bunq2ynab.data.bunq.remote.BunqApi
import app.bunq2ynab.data.bunq.remote.BunqInterceptor
import app.bunq2ynab.data.bunq.remote.BunqRemoteDataSource
import app.bunq2ynab.data.bunq.remote.BunqRemoteDataSourceImpl
import app.bunq2ynab.data.keystore.KeyStoreRepositoryImpl
import app.bunq2ynab.data.utils.network.ServiceFactory
import app.bunq2ynab.domain.repository.BunqRepository
import app.bunq2ynab.domain.repository.KeyStoreRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal abstract class KeystoreModule {

    @Singleton
    @Binds
    abstract fun bindBunqRepository(
        impl: KeyStoreRepositoryImpl
    ): KeyStoreRepository
}
