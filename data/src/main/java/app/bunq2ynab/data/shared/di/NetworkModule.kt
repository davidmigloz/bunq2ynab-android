package app.bunq2ynab.data.shared.di

import app.bunq2ynab.data.utils.json.MoshiFactory
import app.bunq2ynab.data.utils.network.OkHttpFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpBaseClient(): OkHttpClient = OkHttpFactory.createOkHttpClient()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = MoshiFactory.createMoshi()
}
