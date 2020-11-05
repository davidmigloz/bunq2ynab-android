package app.bunq2ynab.data.bunq.di

import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.BunqRepositoryImpl
import app.bunq2ynab.data.bunq.local.BunqLocalDataSource
import app.bunq2ynab.data.bunq.local.BunqLocalDataSourceImpl
import app.bunq2ynab.data.bunq.remote.BunqApi
import app.bunq2ynab.data.bunq.remote.BunqInterceptor
import app.bunq2ynab.data.bunq.remote.BunqRemoteDataSource
import app.bunq2ynab.data.bunq.remote.BunqRemoteDataSourceImpl
import app.bunq2ynab.data.utils.network.ServiceFactory
import app.bunq2ynab.domain.repository.BunqRepository
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

    companion object {
        @Qualifier
        @Retention(AnnotationRetention.RUNTIME)
        annotation class BunqBaseUrl

        @Singleton
        @BunqBaseUrl
        @Provides
        fun provideBunqBaseUrl(): String = BuildConfig.BUNQ_BASE_URL

        @Provides
        fun provideBunqApiService(
            @BunqBaseUrl baseURL: String,
            okHttpBaseClient: OkHttpClient,
            moshiConverterFactory: MoshiConverterFactory,
            bunqInterceptor: BunqInterceptor,
        ): BunqApi = ServiceFactory.createService(
            baseURL = baseURL,
            okHttpBaseClient = okHttpBaseClient,
            moshiConverterFactory = moshiConverterFactory,
            interceptors = listOf(bunqInterceptor)
        )
    }
}
