package app.bunq2ynab.domain.model.bunq

data class BunqInstallation(
    val id: Int,
    val token: BunqAuthToken,
    val serverPublicKey: String
)
