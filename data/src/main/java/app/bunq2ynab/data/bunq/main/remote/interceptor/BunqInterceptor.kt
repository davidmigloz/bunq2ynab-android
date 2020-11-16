package app.bunq2ynab.data.bunq.main.remote.interceptor

import app.bunq2ynab.domain.repository.bunq.BunqAuthManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.util.*
import javax.inject.Inject

/**
 * Docs: https://doc.bunq.com/#/headers
 */
internal class BunqInterceptor @Inject constructor(
    private val bunqAuthManager: BunqAuthManager,
    private val bunqSigner: BunqSigner
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addContentTypeHeader()
                .addCacheHeader()
                .addUserAgentHeader()
                .addBunqHeaders(chain.request())
                .build()
        )
    }

    /**
     * The standard HTTP Cache-Control header is required for all requests.
     */
    private fun Request.Builder.addCacheHeader() =
        header("Cache-Control", "no-cache")

    /**
     * JSON is used as a general data exchange format.
     */
    private fun Request.Builder.addContentTypeHeader() =
        header("Content-Type", "application/json")

    /**
     * The User-Agent header field should contain information about the user agent originating the request.
     * There are no restrictions on the value of this header.
     */
    private fun Request.Builder.addUserAgentHeader() =
        header("User-Agent", "bunq2ynab.app") // TODO attach version

    /**
     * + X-Bunq-Language: The X-Bunq-Language header must contain a preferred language indication.
     *   The value of this header is formatted as a ISO 639-1 language code plus a ISO 3166-1 alpha-2
     *   country code, separated by an underscore. Currently only the languages en_US and nl_NL are supported.
     *   Anything else will default to en_US. Default: en_US. The value of this header is used as default
     *   language setting for responses and error descriptions
     * + X-Bunq-Region: The X-Bunq-Region header must contain the region (country) of the client device.
     *   The value of this header is formatted as a ISO 639-1 language code plus a ISO 3166-1 alpha-2 country code,
     *   separated by an underscore. Default: en_US. The value of this header is used as the default region
     *   for localization formatting.
     * + X-Bunq-Client-Request-Id: This header has to specify an ID with each request that is unique for the logged
     *   in user. There are no restrictions for the format of this ID. However, the server will respond with an
     *   error when the same ID is used again on the same DeviceServer.
     * + X-Bunq-Geolocation: This header has to specify the geolocation of the device. It makes it possible for
     *   bunq to map the geolocation with the payment. ‌ The format of this value is longitude latitude altitude
     *   radius country. The country is expected to be formatted of an ISO 3166-1 alpha-2 country code. When no
     *   geolocation is available or known the header must still be included but can be zero valued.
     * + X-Bunq-Client-Signature: signature of the request body (https://doc.bunq.com/#/signing)
     * + X-Bunq-Client-Authentication: installation Token or session Token
     */
    private fun Request.Builder.addBunqHeaders(request: Request) = runBlocking {
        header("X-Bunq-Language", "en_US")
        header("X-Bunq-Region", "nl_NL")
        header("X-Bunq-Client-Request-Id", UUID.randomUUID().toString())
        header("X-Bunq-Geolocation", "0 0 0 0 000")
        bunqAuthManager.authToken?.value?.let { token ->
            header("X-Bunq-Client-Authentication", token)
        }
        header("X-Bunq-Client-Signature", bunqSigner.sign(request.getBodyAsString()))
    }

    private fun Request.getBodyAsString(): String {
        val requestCopy = this.newBuilder().build()
        val buffer = Buffer()
        requestCopy.body?.writeTo(buffer)
        return buffer.readUtf8()
    }
}
