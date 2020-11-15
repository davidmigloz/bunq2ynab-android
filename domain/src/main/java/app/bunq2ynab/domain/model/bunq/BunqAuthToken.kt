package app.bunq2ynab.domain.model.bunq

data class BunqAuthToken(
    val id: Int,
    val value: String,
    val created: String,
    val updated: String
)
