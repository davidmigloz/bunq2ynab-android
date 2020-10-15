package app.bunq2ynab.data.bunq.remote

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

internal class BunqInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addAuthHeader()
                .addContentTypeHeader()
                .addUserAgentHeader()
                .addAcceptHeaders()
                .build()
        )
    }

    private fun Request.Builder.addAuthHeader() = this.apply {
//        accessToken?.run {
//            header("Authorization", "Bearer $this")
//        }
    }

    private fun Request.Builder.addContentTypeHeader() = this.apply {
//        header("Content-Type", "application/json")
    }

    private fun Request.Builder.addAcceptHeaders() = this.apply {
//        header("Accept", "application/json")
//        header("Accept-Language", Locale.getDefault().language)
    }

    private fun Request.Builder.addUserAgentHeader() = runBlocking {
        header("User-Agent", "bunq2ynab")
    }
}
