package app.bunq2ynab.data.utils.network

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

internal class RetrofitFactory {

    companion object {
        fun createRetrofit(
            baseURL: String,
            okHttpClient: OkHttpClient,
            retrofitConverterFactories: List<Converter.Factory>
        ): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseURL)
            .apply { retrofitConverterFactories.forEach { addConverterFactory(it) } }
            .build()
    }
}
