package app.bunq2ynab.data.bunq.oauth.di

import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.oauth.BunqOAuthRepositoryImpl
import app.bunq2ynab.data.bunq.oauth.local.BunqOAuthLocalDataSource
import app.bunq2ynab.data.bunq.oauth.local.BunqOAuthLocalDataSourceImpl
import app.bunq2ynab.data.bunq.oauth.remote.BunqOAuthApi
import app.bunq2ynab.data.bunq.oauth.remote.BunqOAuthInterceptor
import app.bunq2ynab.data.bunq.oauth.remote.BunqOAuthRemoteDataSource
import app.bunq2ynab.data.bunq.oauth.remote.BunqOAuthRemoteDataSourceImpl
import app.bunq2ynab.data.utils.network.ServiceFactory
import app.bunq2ynab.domain.repository.bunq.BunqOAuthRepository
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
internal abstract class BunqOAuthModule {

    @Singleton
    @Binds
    abstract fun bindBunqOAuthRepository(
        impl: BunqOAuthRepositoryImpl
    ): BunqOAuthRepository

    @Singleton
    @Binds
    abstract fun bindBunqOAuthRemoteDataSource(
        impl: BunqOAuthRemoteDataSourceImpl
    ): BunqOAuthRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindBunqOAuthLocalDataSource(
        impl: BunqOAuthLocalDataSourceImpl
    ): BunqOAuthLocalDataSource

    companion object {
        @Qualifier
        @Retention(AnnotationRetention.RUNTIME)
        annotation class BunqOAuthBaseUrl

        @Singleton
        @BunqOAuthBaseUrl
        @Provides
        fun provideBunqOAuthBaseUrl(): String = BuildConfig.BUNQ_OAUTH_BASE_URL

        @Qualifier
        @Retention(AnnotationRetention.RUNTIME)
        annotation class BunqMoshiConverterFactory

        @Provides
        fun provideBunqOAuthApiService(
            @BunqOAuthBaseUrl baseURL: String,
            okHttpBaseClient: OkHttpClient,
            bunqInterceptor: BunqOAuthInterceptor,
            moshiConverterFactory: MoshiConverterFactory
        ): BunqOAuthApi = ServiceFactory.createService(
            baseURL = baseURL,
            okHttpBaseClient = okHttpBaseClient,
            okHttpInterceptors = listOf(bunqInterceptor),
            retrofitConverterFactories = listOf(moshiConverterFactory)
        )
    }
}
