package app.bunq2ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BunqSessionIdDto(
    val id: Int?
)

@JsonClass(generateAdapter = true)
internal data class BunqSessionUserDto(
    val id: Int?
)
