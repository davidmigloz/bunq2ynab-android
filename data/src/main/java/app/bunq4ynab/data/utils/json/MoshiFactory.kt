package app.bunq4ynab.data.utils.json

import com.squareup.moshi.Moshi

/**
 * Provides a Moshi instance with the base configuration and adapters.
 */
internal class MoshiFactory {

    companion object {
        fun createMoshi(): Moshi {
            return  Moshi.Builder()
                .add(BigDecimalJsonAdapter)
                .build()
        }
    }
}
