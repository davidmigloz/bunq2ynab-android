package app.bunq4ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BunqDeviceRegistrationRequestDto(
    val description: String = "bunq4ynab.app",
    val secret: String,
    val permitted_ips: List<String> = listOf("*")
)
