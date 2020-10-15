package app.bunq2ynab.data.utils.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

internal class ServiceFactory {

    companion object {
        inline fun <reified S> createService(
            baseURL: String,
            okHttpBaseClient: OkHttpClient,
            moshiConverterFactory: MoshiConverterFactory,
            interceptors: List<Interceptor>
        ): S {
            val okHttpClient = okHttpBaseClient.newBuilder().apply {
                // Add custom interceptors at the beginning so that they run first
                // that the ones from the base client (e.g. logging)
                val newInterceptorsList = interceptors + interceptors()
                interceptors().clear()
                interceptors().addAll(newInterceptorsList)
            }.build()
            val retrofit = RetrofitFactory.createRetrofit(okHttpClient, moshiConverterFactory, baseURL)
            return retrofit.create(S::class.java)
        }
    }
}
