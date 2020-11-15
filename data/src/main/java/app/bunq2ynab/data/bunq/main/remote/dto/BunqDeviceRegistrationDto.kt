package app.bunq2ynab.data.bunq.main.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BunqDeviceRegistrationDto(
    val id: Int?
)
