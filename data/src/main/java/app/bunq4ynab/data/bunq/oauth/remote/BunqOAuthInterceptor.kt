package app.bunq4ynab.data.bunq.oauth.remote

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/**
 * Docs: https://doc.bunq.com/#/headers
 */
internal class BunqOAuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addCacheHeader()
                .addContentTypeHeader()
                .addUserAgentHeader()
                .build()
        )
    }

    /**
     * The standard HTTP Cache-Control header is required for all requests.
     */
    private fun Request.Builder.addCacheHeader() = this.apply {
        header("Cache-Control", "no-cache")
    }

    /**
     * JSON is used as a general data exchange format.
     */
    private fun Request.Builder.addContentTypeHeader() = this.apply {
        header("Content-Type", "application/json")
    }

    /**
     * The User-Agent header field should contain information about the user agent originating the request.
     * There are no restrictions on the value of this header.
     */
    private fun Request.Builder.addUserAgentHeader() = runBlocking {
        header("User-Agent", "bunq4ynab.app")
    }
}
