package app.bunq2ynab.data.utils.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal class RetrofitFactory {

    companion object {
        fun createRetrofit(
            okHttpClient: OkHttpClient,
            moshiConverterFactory: MoshiConverterFactory,
            baseURL: String
        ): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseURL)
            .build()
    }
}
