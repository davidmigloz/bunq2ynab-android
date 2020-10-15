package app.bunq2ynab.data.utils.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

internal class OkHttpFactory {

    companion object {
        /**
         * Creates a base http client that allows to share the core (request pooling, disk cache…).
         */
        fun createOkHttpClient() = OkHttpClient.Builder()
            .followRedirects(false)
            .setLoggingInterceptors()
            .setCertificatePinner()
            .setTimeouts()
            .build()
    }
}

private fun OkHttpClient.Builder.setLoggingInterceptors() = this.apply {
    val loggingInterceptor = HttpLoggingInterceptor { message -> // TODO remove in Release flavour
        Log.d("OkHttp", message)
    }
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    addInterceptor(loggingInterceptor)
}

/**
 * Sets certificate pinner if enable.
 */
private fun OkHttpClient.Builder.setCertificatePinner() = this.apply {
    // TODO
}

/**
 * Sets networking timeouts.
 */
private fun OkHttpClient.Builder.setTimeouts() = this
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
