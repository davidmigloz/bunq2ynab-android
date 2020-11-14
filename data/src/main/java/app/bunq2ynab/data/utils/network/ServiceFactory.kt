package app.bunq2ynab.data.utils.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter

internal class ServiceFactory {

    companion object {
        inline fun <reified S> createService(
            baseURL: String,
            okHttpBaseClient: OkHttpClient,
            okHttpInterceptors: List<Interceptor>,
            retrofitConverterFactories: List<Converter.Factory>
        ): S {
            val okHttpClient = okHttpBaseClient.newBuilder().apply {
                // Add custom interceptors at the beginning so that they run first
                // that the ones from the base client (e.g. logging)
                val newInterceptorsList = okHttpInterceptors + interceptors()
                interceptors().clear()
                interceptors().addAll(newInterceptorsList)
            }.build()
            val retrofit = RetrofitFactory.createRetrofit(baseURL, okHttpClient, retrofitConverterFactories)
            return retrofit.create(S::class.java)
        }
    }
}
