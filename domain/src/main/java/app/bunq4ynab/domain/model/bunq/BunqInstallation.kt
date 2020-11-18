package app.bunq4ynab.domain.model.bunq

data class BunqInstallation(
    val id: Int,
    val token: BunqAuthToken,
    val serverPublicKey: String
)
