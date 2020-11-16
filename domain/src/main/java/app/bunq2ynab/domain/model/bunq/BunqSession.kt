package app.bunq2ynab.domain.model.bunq

data class BunqSession(
    val id: Int,
    val token: BunqAuthToken,
    val userId: Int
)
