package app.bunq4ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BunqSessionOpeningRequestDto(
    val secret: String
)
