package app.bunq4ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BunqInstallationIdDto(
    val id: Int?
)

@JsonClass(generateAdapter = true)
internal data class BunqInstallationServerPublicKeyDto(
    val server_public_key: String?
)
