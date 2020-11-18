package app.bunq4ynab.data.bunq.oauth.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BunqOAuthTokenExchangeResponseDto(
    val access_token: String?,
    val token_type: String?,
    val state: String?
)
