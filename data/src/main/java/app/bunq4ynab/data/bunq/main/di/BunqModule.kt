package app.bunq4ynab.data.bunq.main.di

import app.bunq4ynab.data.BuildConfig
import app.bunq4ynab.data.bunq.main.BunqRepositoryImpl
import app.bunq4ynab.data.bunq.main.local.BunqLocalDataSource
import app.bunq4ynab.data.bunq.main.local.BunqLocalDataSourceImpl
import app.bunq4ynab.data.bunq.main.remote.BunqApi
import app.bunq4ynab.data.bunq.main.remote.interceptor.BunqInterceptor
import app.bunq4ynab.data.bunq.main.remote.BunqRemoteDataSource
import app.bunq4ynab.data.bunq.main.remote.BunqRemoteDataSourceImpl
import app.bunq4ynab.data.bunq.main.remote.converter.*
import app.bunq4ynab.data.utils.network.ServiceFactory
import app.bunq4ynab.domain.repository.bunq.BunqRepository
import com.squareup.moshi.Moshi
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

        @Qualifier
        @Retention(AnnotationRetention.RUNTIME)
        annotation class BunqMoshiConverterFactory

        @Singleton
        @BunqMoshiConverterFactory
        @Provides
        fun provideBunqMoshiConverterFactory(
            moshi: Moshi,
            bunqEnvelopFactory: BunqEnvelopFactory,
            bunqPairAdapterFactory: BunqPairAdapterFactory,
            bunqTripleAdapterFactory: BunqTripleAdapterFactory,
            bunqObjectWrapperAdapterFactory: BunqObjectWrapperAdapterFactory
        ): MoshiConverterFactory = MoshiConverterFactory.create(
            moshi.newBuilder()
                .add(bunqEnvelopFactory)
                .add(bunqPairAdapterFactory)
                .add(bunqTripleAdapterFactory)
                .add(bunqObjectWrapperAdapterFactory)
                .build()
        )

        @Provides
        fun provideBunqApiService(
            @BunqBaseUrl baseURL: String,
            okHttpBaseClient: OkHttpClient,
            bunqInterceptor: BunqInterceptor,
            @BunqMoshiConverterFactory moshiConverterFactory: MoshiConverterFactory,
            envelopConverter: EnvelopConverter
        ): BunqApi = ServiceFactory.createService(
            baseURL = baseURL,
            okHttpBaseClient = okHttpBaseClient,
            okHttpInterceptors = listOf(bunqInterceptor),
            retrofitConverterFactories = listOf(envelopConverter, moshiConverterFactory)
        )
    }
}
