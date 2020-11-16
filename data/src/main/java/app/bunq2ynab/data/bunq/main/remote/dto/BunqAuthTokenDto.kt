package app.bunq2ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BunqAuthTokenDto(
    val id: Int?,
    val created: String?,
    val updated: String?,
    val token: String?
)
