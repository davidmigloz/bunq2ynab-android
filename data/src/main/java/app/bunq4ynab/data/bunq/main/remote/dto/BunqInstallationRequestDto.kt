package app.bunq4ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BunqInstallationRequestDto(
    val client_public_key: String
)
