package app.bunq2ynab.data.utils.json

import com.squareup.moshi.Moshi

internal class MoshiFactory {

    companion object {
        fun createMoshi(): Moshi {
            return  Moshi.Builder()
                .add(BigDecimalJsonAdapter)
                .build()
        }
    }
}
