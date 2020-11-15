package app.bunq2ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BunqInstallationRequestDto(
    val client_public_key: String
)
