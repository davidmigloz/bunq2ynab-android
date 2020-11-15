package app.bunq2ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BunqDeviceRegistrationRequestDto(
    val description: String = "bunq2ynab.app",
    val secret: String,
    val permitted_ips: List<String> = listOf("*")
)
